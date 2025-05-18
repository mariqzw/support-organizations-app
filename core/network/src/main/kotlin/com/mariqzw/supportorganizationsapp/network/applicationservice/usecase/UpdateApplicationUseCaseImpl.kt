package com.mariqzw.supportorganizationsapp.network.applicationservice.usecase

import com.mariqzw.supportorganizationsapp.domain.usecase.applicationservice.UpdateApplicationUseCase
import com.mariqzw.supportorganizationsapp.model.request.application.UpdateApplicationRequest
import com.mariqzw.supportorganizationsapp.model.response.ApplicationResponse
import com.mariqzw.supportorganizationsapp.network.applicationservice.ApplicationService

class UpdateApplicationUseCaseImpl(
    private val applicationService: ApplicationService
) : UpdateApplicationUseCase {
    override suspend fun invoke(applicationId: Long, model: UpdateApplicationRequest): Result<ApplicationResponse> =
        applicationService.updateApplication(applicationId, model)
}
