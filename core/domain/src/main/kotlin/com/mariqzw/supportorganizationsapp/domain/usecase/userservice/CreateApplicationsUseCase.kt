package com.mariqzw.supportorganizationsapp.domain.usecase.userservice

import com.mariqzw.supportorganizationsapp.model.request.application.CreateApplicationRequest
import com.mariqzw.supportorganizationsapp.model.response.ApplicationResponse

interface CreateApplicationsUseCase {
    suspend operator fun invoke(model: CreateApplicationRequest) : Result<ApplicationResponse>
}
