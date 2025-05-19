package com.mariqzw.supportorganizationsapp.profile.di

import com.mariqzw.supportorganizationsapp.data.auth.AuthenticationDataStore
import com.mariqzw.supportorganizationsapp.profile.presentation.viewmodels.ProfileScreenViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val provideProfileModule = module {
    viewModel {
        ProfileScreenViewModel(
            updateUserUseCase = get(),
            getUserProfileUseCase = get()
        )
    }
}
