package com.mariqzw.supportorganizationsapp.network.authservice.usecase

import com.mariqzw.supportorganizationsapp.domain.usecase.authservice.RegisterCompanionUseCase
import com.mariqzw.supportorganizationsapp.network.authservice.AuthService
import com.mariqzw.supportorganizationsapp.domain.usecase.authservice.RegisterUseCase
import com.mariqzw.supportorganizationsapp.model.request.auth.RegisterRequest
import com.mariqzw.supportorganizationsapp.model.response.AuthNetworkResponse

class RegisterCompanionUseCaseImpl(
    private val authService: AuthService
) : RegisterCompanionUseCase {

    override suspend fun invoke(model: RegisterRequest): Result<AuthNetworkResponse> =
        authService.registerCompanion(model = model)
}
