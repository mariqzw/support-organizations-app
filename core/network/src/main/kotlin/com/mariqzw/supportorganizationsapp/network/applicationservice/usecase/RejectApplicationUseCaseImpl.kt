package com.mariqzw.supportorganizationsapp.network.applicationservice.usecase

import com.mariqzw.supportorganizationsapp.domain.usecase.applicationservice.RejectApplicationUseCase
import com.mariqzw.supportorganizationsapp.model.response.ApplicationResponse
import com.mariqzw.supportorganizationsapp.network.applicationservice.ApplicationService

class RejectApplicationUseCaseImpl(
    private val applicationService: ApplicationService
) : RejectApplicationUseCase {
    override suspend fun invoke(applicationId: Long): Result<ApplicationResponse> =
        applicationService.rejectApplication(applicationId)
}
