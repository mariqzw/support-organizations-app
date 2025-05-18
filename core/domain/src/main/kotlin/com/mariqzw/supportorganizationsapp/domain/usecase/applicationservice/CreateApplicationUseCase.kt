package com.mariqzw.supportorganizationsapp.domain.usecase.applicationservice

import com.mariqzw.supportorganizationsapp.model.request.application.CreateApplicationRequest
import com.mariqzw.supportorganizationsapp.model.response.ApplicationResponse

interface CreateApplicationUseCase {
    suspend operator fun invoke(model: CreateApplicationRequest) : Result<ApplicationResponse>
}
