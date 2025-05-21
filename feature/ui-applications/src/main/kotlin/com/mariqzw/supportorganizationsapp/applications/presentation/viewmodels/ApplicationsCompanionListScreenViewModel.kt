package com.mariqzw.supportorganizationsapp.applications.presentation.viewmodels

import androidx.compose.runtime.Immutable
import com.mariqzw.supportorganizationsapp.common.presentation.ModelIntent
import com.mariqzw.supportorganizationsapp.common.presentation.Reducer
import com.mariqzw.supportorganizationsapp.common.presentation.UiState
import com.mariqzw.supportorganizationsapp.common.presentation.ViewModel
import com.mariqzw.supportorganizationsapp.model.response.ApplicationResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import androidx.lifecycle.viewModelScope
import com.mariqzw.supportorganizationsapp.domain.usecase.applicationservice.GetAllApplicationsByCompanionUseCase
import com.mariqzw.supportorganizationsapp.domain.usecase.applicationservice.GetAllNewWithoutCompanionUseCase
import kotlinx.coroutines.launch

class ApplicationsCompanionListViewModel(
    private val getAllApplicationsByCompanionUseCase: GetAllApplicationsByCompanionUseCase,
    private val getAllNewWithoutCompanionUseCaseImpl: GetAllNewWithoutCompanionUseCase
) : ViewModel<ApplicationsCompanionListState, ApplicationsCompanionListIntent>() {

    private val reducer = ApplicationsListReducer(ApplicationsCompanionListState.Init)

    override val state: StateFlow<ApplicationsCompanionListState>
        get() = reducer.state

    private val _applicationsMe = MutableStateFlow<List<ApplicationResponse>>(emptyList())
    val applicationsMe: StateFlow<List<ApplicationResponse>> = _applicationsMe.asStateFlow()

    private val _applicationsFree = MutableStateFlow<List<ApplicationResponse>>(emptyList())
    val applicationsFree: StateFlow<List<ApplicationResponse>> = _applicationsFree.asStateFlow()

    init {
        refresh()
    }

    fun refresh() = viewModelScope.launch {
        reducer.sendIntent(ApplicationsCompanionListIntent.Load)

        getAllApplicationsByCompanionUseCase()
            .onSuccess { list ->
                _applicationsMe.value = list
                reducer.sendIntent(ApplicationsCompanionListIntent.LoadSuccess(list))
            }

        getAllNewWithoutCompanionUseCaseImpl()
            .onSuccess { list ->
                _applicationsFree.value = list
                reducer.sendIntent(ApplicationsCompanionListIntent.LoadSuccess(list))
            }
    }

    private class ApplicationsListReducer(
        initial: ApplicationsCompanionListState.Init
    ) : Reducer<ApplicationsCompanionListState, ApplicationsCompanionListIntent>(initial) {
        override fun reduce(
            oldState: ApplicationsCompanionListState,
            intent: ApplicationsCompanionListIntent
        ) {
            when (intent) {
                is ApplicationsCompanionListIntent.Load -> setState(ApplicationsCompanionListState.Loading)

                is ApplicationsCompanionListIntent.LoadSuccess -> setState(ApplicationsCompanionListState.Success)
            }
        }
    }
}

@Immutable
sealed class ApplicationsCompanionListIntent : ModelIntent {
    data object Load : ApplicationsCompanionListIntent()
    data class LoadSuccess(val applications: List<ApplicationResponse>) : ApplicationsCompanionListIntent()
}

@Immutable
sealed class ApplicationsCompanionListState : UiState {
    data object Init : ApplicationsCompanionListState()
    data object Loading : ApplicationsCompanionListState()
    data object Success : ApplicationsCompanionListState()
    data class Error(val message: String?) : ApplicationsCompanionListState()
}
