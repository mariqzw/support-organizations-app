package com.mariqzw.supportorganizationsapp.applications.presentation.viewmodels

import androidx.compose.runtime.Immutable
import androidx.lifecycle.viewModelScope
import com.mariqzw.supportorganizationsapp.common.presentation.ModelIntent
import com.mariqzw.supportorganizationsapp.common.presentation.Reducer
import com.mariqzw.supportorganizationsapp.common.presentation.UiState
import com.mariqzw.supportorganizationsapp.common.presentation.ViewModel
import com.mariqzw.supportorganizationsapp.domain.usecase.applicationservice.CancelApplicationUseCase
import com.mariqzw.supportorganizationsapp.domain.usecase.applicationservice.DeleteApplicationUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ApplicationDetailsViewModel(
    private val deleteApplicationUseCase: DeleteApplicationUseCase,
    private val cancelApplicationUseCase: CancelApplicationUseCase
) : ViewModel<ApplicationDetailsState, ApplicationDetailsIntent>() {

    private val reducer = ApplicationDetailsReducer(ApplicationDetailsState.Init)
    override val state = reducer.state

    private val _alertMessage = MutableStateFlow<String?>(null)
    val alertMessage: StateFlow<String?> = _alertMessage.asStateFlow()

    private val _navigateBack = MutableStateFlow(false)
    val navigateBack: StateFlow<Boolean> = _navigateBack

    fun dismissAlert() {
        _alertMessage.value = null
    }

    fun onNavigated() {
        _navigateBack.value = false
    }

    fun onActionClick(applicationId: Long, status: String) {
        viewModelScope.launch {
            when (status) {
                "Создана" -> {
                    deleteApplicationUseCase(applicationId)
                        .onSuccess {
                            reducer.sendIntent(ApplicationDetailsIntent.DeleteSuccess)
                            _navigateBack.value = true
                        }
                        .onFailure {
                            reducer.sendIntent(
                                ApplicationDetailsIntent.Failure(it.message ?: "Ошибка при удалении")
                            )
                        }
                }

                "Назначена" -> {
                    cancelApplicationUseCase(applicationId)
                        .onSuccess {
                            reducer.sendIntent(ApplicationDetailsIntent.CancelSuccess)
                            _navigateBack.value = true
                        }
                        .onFailure {
                            reducer.sendIntent(
                                ApplicationDetailsIntent.Failure(it.message ?: "Ошибка при отмене")
                            )
                        }
                }

                else -> {
                    reducer.sendIntent(ApplicationDetailsIntent.Failure("Недопустимое действие для текущего статуса"))
                }
            }
        }
    }

    private inner class ApplicationDetailsReducer(initial: ApplicationDetailsState) :
        Reducer<ApplicationDetailsState, ApplicationDetailsIntent>(initial) {

        override fun reduce(oldState: ApplicationDetailsState, intent: ApplicationDetailsIntent) {
            when (intent) {
                ApplicationDetailsIntent.DeleteSuccess -> {
                    setState(ApplicationDetailsState.Success("Заявка успешно удалена"))
                    _alertMessage.value = "Заявка успешно удалена"
                }

                ApplicationDetailsIntent.CancelSuccess -> {
                    setState(ApplicationDetailsState.Success("Заявка успешно отменена"))
                    _alertMessage.value = "Заявка успешно отменена"
                }

                is ApplicationDetailsIntent.Failure -> {
                    setState(ApplicationDetailsState.Error(intent.error))
                    _alertMessage.value = "Ошибка: ${intent.error}"
                }
            }
        }
    }
}

@Immutable
sealed class ApplicationDetailsIntent : ModelIntent {
    data object DeleteSuccess : ApplicationDetailsIntent()
    data object CancelSuccess : ApplicationDetailsIntent()
    data class Failure(val error: String) : ApplicationDetailsIntent()
}

@Immutable
sealed class ApplicationDetailsState : UiState {
    data object Init : ApplicationDetailsState()
    data class Success(val message: String) : ApplicationDetailsState()
    data class Error(val error: String) : ApplicationDetailsState()
}
