package com.mariqzw.supportorganizationsapp.di

import com.mariqzw.supportorganizationsapp.ui.presentation.viewmodel.MainActivityViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val provideViewModelModule = module {
    viewModel {
        MainActivityViewModel(
            authDataStore = get()
        )
    }
}
