package com.mariqzw.supportorganizationsapp.network.authservice.usecase

import com.mariqzw.supportorganizationsapp.network.authservice.AuthService
import com.mariqzw.supportorganizationsapp.domain.usecase.authservice.RefreshTokenUseCase
import com.mariqzw.supportorganizationsapp.model.request.auth.RefreshToken
import com.mariqzw.supportorganizationsapp.model.response.AuthNetworkResponse

class RefreshTokenUseCaseImpl(
    private val authService: AuthService
) : RefreshTokenUseCase {

    override suspend fun invoke(model: RefreshToken): Result<AuthNetworkResponse> =
        authService.refreshToken(model)
}
