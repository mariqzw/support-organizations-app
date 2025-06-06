package com.mariqzw.supportorganizationsapp.applications.di

import com.mariqzw.supportorganizationsapp.applications.presentation.viewmodels.ApplicationDetailsCompanionViewModel
import com.mariqzw.supportorganizationsapp.applications.presentation.viewmodels.ApplicationDetailsViewModel
import com.mariqzw.supportorganizationsapp.applications.presentation.viewmodels.ApplicationsCompanionListViewModel
import com.mariqzw.supportorganizationsapp.applications.presentation.viewmodels.ApplicationsListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val provideApplicationsModule = module {
    viewModel {
        ApplicationsListViewModel(
            getAllApplicationsUseCase = get()
        )
    }

    viewModel {
        ApplicationsCompanionListViewModel(
            getAllApplicationsByCompanionUseCase = get(),
            getAllNewWithoutCompanionUseCaseImpl = get()
        )
    }

    viewModel {
        ApplicationDetailsViewModel(
            deleteApplicationUseCase = get(),
            cancelApplicationUseCase = get()
        )
    }

    viewModel {
        ApplicationDetailsCompanionViewModel(
            completeApplicationUseCase = get(),
            rejectApplicationUseCase = get(),
            assigneeApplicationUseCase = get()
        )
    }
}
