package com.mariqzw.supportorganizationsapp.network.authservice.usecase

import com.mariqzw.supportorganizationsapp.network.authservice.AuthService
import com.mariqzw.supportorganizationsapp.domain.usecase.authservice.RegisterUseCase
import com.mariqzw.supportorganizationsapp.model.request.auth.RegisterRequest
import com.mariqzw.supportorganizationsapp.model.response.AuthNetworkResponse

class RegisterUseCaseImpl(
    private val authService: AuthService
) : RegisterUseCase {

    override suspend fun invoke(model: RegisterRequest): Result<AuthNetworkResponse> =
        authService.register(model = model)
}
