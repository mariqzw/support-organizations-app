package com.mariqzw.supportorganizationsapp.addapplications.di

import com.mariqzw.supportorganizationsapp.addapplications.presentation.viewmodels.AddApplicationViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val provideAddApplicationsModule = module {
    viewModel {
        AddApplicationViewModel(
            createApplicationUseCase = get()
        )
    }
}
