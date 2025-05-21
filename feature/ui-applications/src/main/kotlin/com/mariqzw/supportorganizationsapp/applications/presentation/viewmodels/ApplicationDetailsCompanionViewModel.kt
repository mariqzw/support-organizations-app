package com.mariqzw.supportorganizationsapp.applications.presentation.viewmodels

import androidx.compose.runtime.Immutable
import androidx.lifecycle.viewModelScope
import com.mariqzw.supportorganizationsapp.common.presentation.ModelIntent
import com.mariqzw.supportorganizationsapp.common.presentation.Reducer
import com.mariqzw.supportorganizationsapp.common.presentation.UiState
import com.mariqzw.supportorganizationsapp.common.presentation.ViewModel
import com.mariqzw.supportorganizationsapp.domain.usecase.applicationservice.AssigneeApplicationUseCase
import com.mariqzw.supportorganizationsapp.domain.usecase.applicationservice.CompleteApplicationUseCase
import com.mariqzw.supportorganizationsapp.domain.usecase.applicationservice.RejectApplicationUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ApplicationDetailsCompanionViewModel (
    private val assigneeApplicationUseCase: AssigneeApplicationUseCase,
    private val rejectApplicationUseCase: RejectApplicationUseCase,
    private val completeApplicationUseCase: CompleteApplicationUseCase
) : ViewModel<AccompanyingApplicationState, AccompanyingApplicationIntent>() {

    private val reducer = AccompanyingApplicationReducer(AccompanyingApplicationState.Init)
    override val state = reducer.state

    private val _alertMessage = MutableStateFlow<String?>(null)
    val alertMessage: StateFlow<String?> = _alertMessage.asStateFlow()

    private val _navigateBack = MutableStateFlow(false)
    val navigateBack: StateFlow<Boolean> = _navigateBack.asStateFlow()

    fun dismissAlert() {
        _alertMessage.value = null
    }

    fun onNavigated() {
        _navigateBack.value = false
    }

    fun onActionClick(applicationId: Long, status: String, action: ActionType) {
        viewModelScope.launch {
            when (action) {
                ActionType.REJECT -> {
                    rejectApplicationUseCase(applicationId)
                        .onSuccess {
                            reducer.sendIntent(AccompanyingApplicationIntent.RejectSuccess)
                            _navigateBack.value = true
                        }
                        .onFailure {
                            reducer.sendIntent(
                                AccompanyingApplicationIntent.Failure(it.message ?: "Ошибка при отклонении")
                            )
                        }
                }

                ActionType.ASSIGN -> {
                    assigneeApplicationUseCase(applicationId)
                        .onSuccess {
                            reducer.sendIntent(AccompanyingApplicationIntent.AssignedSuccess)
                            _navigateBack.value = true
                        }
                        .onFailure {
                            reducer.sendIntent(
                                AccompanyingApplicationIntent.Failure(it.message ?: "Ошибка при назначении")
                            )
                        }
                }

                ActionType.COMPLETE -> {
                    completeApplicationUseCase(applicationId)
                        .onSuccess {
                            reducer.sendIntent(AccompanyingApplicationIntent.CompletedSuccess)
                            _navigateBack.value = true
                        }
                        .onFailure {
                            reducer.sendIntent(
                                AccompanyingApplicationIntent.Failure(it.message ?: "Ошибка при завершении")
                            )
                        }
                }
            }
        }
    }

    private inner class AccompanyingApplicationReducer(initial: AccompanyingApplicationState) :
        Reducer<AccompanyingApplicationState, AccompanyingApplicationIntent>(initial) {

        override fun reduce(oldState: AccompanyingApplicationState, intent: AccompanyingApplicationIntent) {
            when (intent) {
                AccompanyingApplicationIntent.RejectSuccess -> {
                    setState(AccompanyingApplicationState.Success("Заявка отклонена"))
                    _alertMessage.value = "Заявка отклонена"
                }

                AccompanyingApplicationIntent.AssignedSuccess -> {
                    setState(AccompanyingApplicationState.Success("Вы назначены на заявку"))
                    _alertMessage.value = "Вы назначены на заявку"
                }

                AccompanyingApplicationIntent.CompletedSuccess -> {
                    setState(AccompanyingApplicationState.Success("Заявка выполнена"))
                    _alertMessage.value = "Заявка выполнена"
                }

                is AccompanyingApplicationIntent.Failure -> {
                    setState(AccompanyingApplicationState.Error(intent.error))
                    _alertMessage.value = "Ошибка: ${intent.error}"
                }
            }
        }
    }
}

enum class ActionType {
    REJECT, ASSIGN, COMPLETE
}

@Immutable
sealed class AccompanyingApplicationIntent : ModelIntent {
    data object RejectSuccess : AccompanyingApplicationIntent()
    data object AssignedSuccess : AccompanyingApplicationIntent()
    data object CompletedSuccess : AccompanyingApplicationIntent()
    data class Failure(val error: String) : AccompanyingApplicationIntent()
}

@Immutable
sealed class AccompanyingApplicationState : UiState {
    data object Init : AccompanyingApplicationState()
    data class Success(val message: String) : AccompanyingApplicationState()
    data class Error(val error: String) : AccompanyingApplicationState()
}
