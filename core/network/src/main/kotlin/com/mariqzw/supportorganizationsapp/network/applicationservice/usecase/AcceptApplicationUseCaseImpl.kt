package com.mariqzw.supportorganizationsapp.network.applicationservice.usecase

import com.mariqzw.supportorganizationsapp.domain.usecase.applicationservice.AcceptApplicationUseCase
import com.mariqzw.supportorganizationsapp.model.response.ApplicationResponse
import com.mariqzw.supportorganizationsapp.network.applicationservice.ApplicationService

class AcceptApplicationUseCaseImpl(
    private val applicationService: ApplicationService
) : AcceptApplicationUseCase {
    override suspend fun invoke(applicationId: Long, companionId: Long): Result<ApplicationResponse> =
        applicationService.acceptApplication(applicationId, companionId)
}
