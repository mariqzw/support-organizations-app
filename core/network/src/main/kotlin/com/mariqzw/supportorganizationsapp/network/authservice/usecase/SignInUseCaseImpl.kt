package com.mariqzw.supportorganizationsapp.network.authservice.usecase

import com.mariqzw.supportorganizationsapp.network.authservice.AuthService
import com.mariqzw.supportorganizationsapp.domain.usecase.authservice.SignInUseCase
import com.mariqzw.supportorganizationsapp.model.request.auth.AuthRequest
import com.mariqzw.supportorganizationsapp.model.response.AuthNetworkResponse


class SignInUseCaseImpl(
    private val authService: AuthService
) : SignInUseCase {

    override suspend fun invoke(model: AuthRequest): Result<AuthNetworkResponse> =
        authService.signIn(model)
}
