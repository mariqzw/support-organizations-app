package com.mariqzw.supportorganizationsapp.applications.di

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
}
