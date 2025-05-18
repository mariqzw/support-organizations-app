package com.mariqzw.supportorganizationsapp.network.applicationservice.usecase

import com.mariqzw.supportorganizationsapp.domain.usecase.applicationservice.CreateApplicationUseCase
import com.mariqzw.supportorganizationsapp.model.request.application.CreateApplicationRequest
import com.mariqzw.supportorganizationsapp.network.applicationservice.ApplicationService

class CreateApplicationUseCaseImpl(
    private val applicationService: ApplicationService
) : CreateApplicationUseCase {
    override suspend fun invoke(model: CreateApplicationRequest) =
        applicationService.createApplication(model)
}
