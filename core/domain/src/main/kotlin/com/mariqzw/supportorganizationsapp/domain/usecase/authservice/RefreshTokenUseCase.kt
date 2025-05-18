package com.mariqzw.supportorganizationsapp.domain.usecase.authservice

import com.mariqzw.supportorganizationsapp.domain.usecase.UseCase
import com.mariqzw.supportorganizationsapp.model.request.auth.RefreshToken
import com.mariqzw.supportorganizationsapp.model.response.AuthNetworkResponse

interface RefreshTokenUseCase : UseCase {
    suspend operator fun invoke(model: RefreshToken): Result<AuthNetworkResponse>
}
