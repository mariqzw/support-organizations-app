package com.mariqzw.supportorganizationsapp.domain.usecase.authservice

import com.mariqzw.supportorganizationsapp.domain.usecase.UseCase
import com.mariqzw.supportorganizationsapp.model.request.auth.RegisterRequest
import com.mariqzw.supportorganizationsapp.model.response.AuthNetworkResponse

interface RegisterUseCase : UseCase {
    suspend operator fun invoke(model: RegisterRequest): Result<AuthNetworkResponse>
}
