package com.mariqzw.supportorganizationsapp.domain.usecase.authservice

import com.mariqzw.supportorganizationsapp.domain.usecase.UseCase
import com.mariqzw.supportorganizationsapp.model.request.auth.AuthRequest
import com.mariqzw.supportorganizationsapp.model.response.AuthNetworkResponse

interface SignInUseCase : UseCase {
    suspend operator fun invoke(model: AuthRequest): Result<AuthNetworkResponse>
}
