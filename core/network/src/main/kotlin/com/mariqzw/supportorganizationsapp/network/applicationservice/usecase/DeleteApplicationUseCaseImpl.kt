package com.mariqzw.supportorganizationsapp.network.applicationservice.usecase

import com.mariqzw.supportorganizationsapp.domain.usecase.applicationservice.DeleteApplicationUseCase
import com.mariqzw.supportorganizationsapp.network.applicationservice.ApplicationService

class DeleteApplicationUseCaseImpl(
    private val applicationService: ApplicationService
) : DeleteApplicationUseCase {
    override suspend fun invoke(applicationId: Long) =
        applicationService.deleteApplication(applicationId)
}
