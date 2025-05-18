package com.mariqzw.supportorganizationsapp.auth.di

import com.mariqzw.supportorganizationsapp.auth.presentation.viewmodels.AuthorizationViewModel
import com.mariqzw.supportorganizationsapp.auth.presentation.viewmodels.RegistrationViewModel
import com.mariqzw.supportorganizationsapp.auth.presentation.viewmodels.SignUpViewModel
import com.mariqzw.supportorganizationsapp.auth.presentation.viewmodels.SplashScreenViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val provideAuthModule = module {
    viewModel {
        AuthorizationViewModel(
            signIn = get()
        )
    }

    viewModel {
        SplashScreenViewModel(
            refreshTokenUseCase = get(),
            authenticationDataStore = get()
        )
    }

    viewModel {
        RegistrationViewModel(
            registerUseCase = get()
        )
    }

    viewModel {
        SignUpViewModel(
            registerUseCase = get()
        )
    }
}
