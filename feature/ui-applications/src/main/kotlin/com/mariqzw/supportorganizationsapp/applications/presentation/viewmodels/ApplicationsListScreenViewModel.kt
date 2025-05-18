package com.mariqzw.supportorganizationsapp.applications.presentation.viewmodels

import androidx.compose.runtime.Immutable
import com.mariqzw.supportorganizationsapp.common.presentation.ModelIntent
import com.mariqzw.supportorganizationsapp.common.presentation.Reducer
import com.mariqzw.supportorganizationsapp.common.presentation.UiState
import com.mariqzw.supportorganizationsapp.common.presentation.ViewModel
import com.mariqzw.supportorganizationsapp.domain.usecase.applicationservice.GetAllApplicationsUseCase
import com.mariqzw.supportorganizationsapp.model.response.ApplicationResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ApplicationsListViewModel(
    private val getAllApplicationsUseCase: GetAllApplicationsUseCase
) : ViewModel<ApplicationsListState, ApplicationsListIntent>() {

    private val reducer = ApplicationsListReducer(ApplicationsListState.Init)

    override val state: StateFlow<ApplicationsListState>
        get() = reducer.state

    private val _applications = MutableStateFlow<List<ApplicationResponse>>(emptyList())
    val applications: StateFlow<List<ApplicationResponse>> = _applications.asStateFlow()

    init {
        // сразу загружаем данные
        refresh()
    }

    /** триггер для перезагрузки списка */
    fun refresh() = viewModelScope.launch {
        reducer.sendIntent(ApplicationsListIntent.Load)

        getAllApplicationsUseCase()
            .onSuccess { list ->
                _applications.value = list
                reducer.sendIntent(ApplicationsListIntent.LoadSuccess(list))
            }
    }

    private class ApplicationsListReducer(
        initial: ApplicationsListState.Init
    ) : Reducer<ApplicationsListState, ApplicationsListIntent>(initial) {
        override fun reduce(
            oldState: ApplicationsListState,
            intent: ApplicationsListIntent
        ) {
            when (intent) {
                is ApplicationsListIntent.Load -> setState(ApplicationsListState.Loading)

                is ApplicationsListIntent.LoadSuccess -> setState(ApplicationsListState.Success)
            }
        }
    }
}

@Immutable
sealed class ApplicationsListIntent : ModelIntent {
    data object Load : ApplicationsListIntent()
    data class LoadSuccess(val applications: List<ApplicationResponse>) : ApplicationsListIntent()
}

@Immutable
sealed class ApplicationsListState : UiState {
    data object Init : ApplicationsListState()
    data object Loading : ApplicationsListState()
    data object Success : ApplicationsListState()
    data class Error(val message: String?) : ApplicationsListState()
}
