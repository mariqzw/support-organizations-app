package com.mariqzw.supportorganizationsapp.auth.presentation.viewmodels

import androidx.compose.runtime.Immutable
import androidx.lifecycle.viewModelScope
import com.mariqzw.supportorganizationsapp.common.presentation.ModelIntent
import com.mariqzw.supportorganizationsapp.common.presentation.Reducer
import com.mariqzw.supportorganizationsapp.common.presentation.UiState
import com.mariqzw.supportorganizationsapp.common.presentation.ViewModel
import com.mariqzw.supportorganizationsapp.domain.usecase.authservice.RegisterCompanionUseCase
import com.mariqzw.supportorganizationsapp.domain.usecase.authservice.RegisterUseCase
import com.mariqzw.supportorganizationsapp.domain.usecase.authservice.SignInUseCase
import com.mariqzw.supportorganizationsapp.model.request.auth.RegisterRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

class SignUpViewModel(
    private val registerUseCase: RegisterCompanionUseCase
) : ViewModel<SignUpScreenState, SignUpScreenIntent>() {

    /* MVI‑infrastructure */
    private val reducer = SignUpScreenReducer(SignUpScreenState.Init)
    override val state: Flow<SignUpScreenState>
        get() = reducer.state

    /* form fields */
    private val _number = MutableStateFlow("")
    private val _email = MutableStateFlow("")
    private val _firstName = MutableStateFlow("")
    private val _lastName = MutableStateFlow("")
    private val _password = MutableStateFlow("")

    /* errors */
    private val _numberErr = MutableStateFlow<String?>(null)
    private val _emailErr = MutableStateFlow<String?>(null)
    private val _firstNameErr = MutableStateFlow<String?>(null)
    private val _lastNameErr = MutableStateFlow<String?>(null)
    private val _passwordErr = MutableStateFlow<String?>(null)

    /* public flows */
    val number: StateFlow<String> get() = _number
    val email: StateFlow<String> get() = _email
    val firstName: StateFlow<String> get() = _firstName
    val lastName: StateFlow<String> get() = _lastName
    val password: StateFlow<String> get() = _password

    val numberErr: StateFlow<String?> get() = _numberErr
    val emailErr: StateFlow<String?> get() = _emailErr
    val firstNameErr: StateFlow<String?> get() = _firstNameErr
    val lastNameErr: StateFlow<String?> get() = _lastNameErr
    val passwordErr: StateFlow<String?> get() = _passwordErr

    /* public callbacks */
    fun onNumberChanged(v: String) = dispatch(SignUpScreenIntent.NumberChanged(v))
    fun onEmailChanged(v: String) = dispatch(SignUpScreenIntent.EmailChanged(v))
    fun onFirstNameChanged(v: String) = dispatch(SignUpScreenIntent.FirstNameChanged(v))
    fun onLastNameChanged(v: String) = dispatch(SignUpScreenIntent.LastNameChanged(v))
    fun onPasswordChanged(v: String) = dispatch(SignUpScreenIntent.PasswordChanged(v))

    fun submit() = dispatch(SignUpScreenIntent.Submit)

    /* dispatcher */
    private fun dispatch(intent: SignUpScreenIntent) {
        when (intent) {
            is SignUpScreenIntent.NumberChanged -> {
                _number.value = intent.value
                _numberErr.value = validatePhone(intent.value)
            }

            is SignUpScreenIntent.EmailChanged -> {
                _email.value = intent.value
                _emailErr.value = validateEmail(intent.value)
            }

            is SignUpScreenIntent.FirstNameChanged -> {
                _firstName.value = intent.value
                _firstNameErr.value = validateName(intent.value, "Имя")
            }

            is SignUpScreenIntent.LastNameChanged -> {
                _lastName.value = intent.value
                _lastNameErr.value = validateName(intent.value, "Фамилия")
            }

            is SignUpScreenIntent.PasswordChanged -> {
                _password.value = intent.value
                _passwordErr.value = validatePassword(intent.value)
            }


            SignUpScreenIntent.Submit -> processSubmit()

            else -> {}
        }
        reducer.sendIntent(intent)
    }

    /* business logic */
    private fun processSubmit() {
        viewModelScope.launch {
            if (!isFormValid()) {
                reducer.sendIntent(SignUpScreenIntent.SubmitFailure("Заполните все обязательные поля"))
                // обновляем все ошибки сразу
                _firstNameErr.value = validateName(_firstName.value, "Имя")
                _lastNameErr.value = validateName(_lastName.value, "Фамилия")
                _numberErr.value = validatePhone(_number.value)
                _emailErr.value = validateEmail(_email.value)
                _passwordErr.value = validatePassword(_password.value)
                return@launch
            }

            reducer.sendIntent(SignUpScreenIntent.SubmitStart)

            val body = RegisterRequest(
                email = _email.value.trim(),
                phoneNumber = _number.value.trim(),
                firstName = _firstName.value.trim(),
                lastName = _lastName.value.trim(),
                password = _password.value.trim(),
            )

            registerUseCase(body)
                .onSuccess {
                    reducer.sendIntent(SignUpScreenIntent.SubmitSuccess)
                    clearForm()
                }
                .onFailure { t ->
                    reducer.sendIntent(SignUpScreenIntent.SubmitFailure(t.message.toString()))
                }

            Timber.tag("request Register").d(body.toString())
        }
    }

    private fun isFormValid(): Boolean =
        _firstName.value.isNotBlank() &&
                _lastName.value.isNotBlank() &&
                _number.value.isNotBlank() &&
                _email.value.isNotBlank() &&
                _password.value.isNotBlank()

    private fun clearForm() {
        _firstName.value = ""
        _lastName.value = ""
        _number.value = ""
        _email.value = ""
        _password.value = ""
    }

    /* validation helpers -------------------------------------------------- */
    private fun validateName(v: String, fieldName: String) = when {
        v.isBlank() -> "Введите $fieldName"
        v.any { it.isDigit() } -> "$fieldName не должно содержать цифр"
        else -> null
    }

    private fun validatePhone(v: String) = when {
        v.isBlank() -> "Введите номер телефона"
        !v.all { it.isDigit() } -> "Номер телефона должен содержать только цифры"
        v.length !in 10..15 -> "Длина номера должна быть 10–15 символов"
        else -> null
    }

    private fun validateEmail(v: String) = when {
        v.isBlank() -> "Введите e‑mail"
        !android.util.Patterns.EMAIL_ADDRESS.matcher(v).matches() -> "Неверный формат e‑mail"
        else -> null
    }

    private fun validatePassword(v: String) = when {
        v.isBlank() -> "Введите пароль"
        v.length < 8 -> "Пароль должен быть не короче 6 символов"
        else -> null
    }

    /* Reducer -------------------------------------------------------------- */
    private class SignUpScreenReducer(initial: SignUpScreenState) :
        Reducer<SignUpScreenState, SignUpScreenIntent>(initial) {

        override fun reduce(oldState: SignUpScreenState, intent: SignUpScreenIntent) {
            when (intent) {
                SignUpScreenIntent.SubmitStart ->
                    setState(SignUpScreenState.Loading)

                is SignUpScreenIntent.SubmitSuccess ->
                    setState(
                        SignUpScreenState.Success
                    )

                is SignUpScreenIntent.SubmitFailure ->
                    setState(SignUpScreenState.Error(intent.error))

                else -> Unit
            }
        }
    }
}

/* INTENTS ----------------------------------------------------------------- */
@Immutable
sealed class SignUpScreenIntent : ModelIntent {
    data class FirstNameChanged(val value: String) : SignUpScreenIntent()
    data class LastNameChanged(val value: String) : SignUpScreenIntent()
    data class NumberChanged(val value: String) : SignUpScreenIntent()
    data class EmailChanged(val value: String) : SignUpScreenIntent()
    data class PasswordChanged(val value: String) : SignUpScreenIntent()

    data object Submit : SignUpScreenIntent()
    data object SubmitStart : SignUpScreenIntent()
    data object SubmitSuccess : SignUpScreenIntent()

    data class SubmitFailure(val error: String) : SignUpScreenIntent()
}

/* STATE ------------------------------------------------------------------- */
@Immutable
sealed class SignUpScreenState : UiState {
    data object Init : SignUpScreenState()

    data object Loading : SignUpScreenState()

    data object Success : SignUpScreenState()

    data class Error(val message: String) : SignUpScreenState()
}