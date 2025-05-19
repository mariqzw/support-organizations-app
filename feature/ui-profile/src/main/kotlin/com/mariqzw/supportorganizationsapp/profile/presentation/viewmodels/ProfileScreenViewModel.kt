package com.mariqzw.supportorganizationsapp.profile.presentation.viewmodels

import androidx.compose.runtime.Immutable
import androidx.lifecycle.viewModelScope
import com.mariqzw.supportorganizationsapp.common.presentation.ModelIntent
import com.mariqzw.supportorganizationsapp.common.presentation.Reducer
import com.mariqzw.supportorganizationsapp.common.presentation.UiState
import com.mariqzw.supportorganizationsapp.common.presentation.ViewModel
import com.mariqzw.supportorganizationsapp.domain.usecase.userservice.GetUserProfileUseCase
import com.mariqzw.supportorganizationsapp.domain.usecase.userservice.UpdateUserUseCase
import com.mariqzw.supportorganizationsapp.model.request.UpdateUserRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

class ProfileScreenViewModel(
    private val updateUserUseCase: UpdateUserUseCase,
    private val getUserProfileUseCase: GetUserProfileUseCase
) : ViewModel<ProfileScreenState, ProfileScreenIntent>() {

    private val reducer = ProfileScreenReducer(ProfileScreenState.Init)
    override val state: Flow<ProfileScreenState> get() = reducer.state

    private val _name = MutableStateFlow("")
    private val _surname = MutableStateFlow("")
    private val _phoneNumber = MutableStateFlow("")
    private val _email = MutableStateFlow("")

    private val _alertMessage = MutableStateFlow<String?>(null)
    val alertMessage: StateFlow<String?> get() = _alertMessage

    val name: StateFlow<String> get() = _name
    val surname: StateFlow<String> get() = _surname
    val phoneNumber: StateFlow<String> get() = _phoneNumber
    val email: StateFlow<String> get() = _email

    fun updateUser() {
        viewModelScope.launch {
            val request = UpdateUserRequest(
                firstName = _name.value.trim(),
                lastName = _surname.value.trim(),
                phoneNumber = _phoneNumber.value.trim(),
                email = _email.value.trim()
            )

            updateUserUseCase(model = request)
                .onSuccess { reducer.sendIntent(ProfileScreenIntent.UpdateSuccess) }
                .onFailure { reducer.sendIntent(ProfileScreenIntent.UpdateFailure(it.message ?: "Ошибка обновления данных")) }
        }
    }

    fun loadUserData() {
        viewModelScope.launch {
            getUserProfileUseCase().onSuccess { user ->
                _name.value = user.firstName
                _surname.value = user.lastName
                _phoneNumber.value = user.phoneNumber
                _email.value = user.email
            }.onFailure {
                Timber.d("Fail to load user data")
            }
        }
    }

    fun dismissAlert() {
        _alertMessage.value = null
    }

    fun onNameChanged(newName: String) { _name.value = newName }
    fun onSurnameChanged(newSurname: String) { _surname.value = newSurname }
    fun onPhoneNumberChanged(newPhoneNumber: String) { _phoneNumber.value = newPhoneNumber }
    fun onEmailChanged(newEmail: String) { _email.value = newEmail }

    private inner class ProfileScreenReducer(initial: ProfileScreenState) :
        Reducer<ProfileScreenState, ProfileScreenIntent>(initial) {

        override fun reduce(oldState: ProfileScreenState, intent: ProfileScreenIntent) {
            when (intent) {
                ProfileScreenIntent.UpdateSuccess -> {
                    setState(ProfileScreenState.Success)
                    _alertMessage.value = "Данные успешно обновлены"
                }

                is ProfileScreenIntent.UpdateFailure -> {
                    setState(ProfileScreenState.Error(intent.error))
                    _alertMessage.value = "Ошибка: ${intent.error}"
                }
            }
        }
    }
}

@Immutable
sealed class ProfileScreenIntent : ModelIntent {
    data object UpdateSuccess : ProfileScreenIntent()
    data class UpdateFailure(val error: String) : ProfileScreenIntent()
}

@Immutable
sealed class ProfileScreenState : UiState {
    data object Init : ProfileScreenState()
    data object Success : ProfileScreenState()
    data class Error(val message: String) : ProfileScreenState()
}
