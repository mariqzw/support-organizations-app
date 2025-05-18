package com.mariqzw.supportorganizationsapp.network.applicationservice.usecase

import com.mariqzw.supportorganizationsapp.domain.usecase.applicationservice.CompleteApplicationUseCase
import com.mariqzw.supportorganizationsapp.network.applicationservice.ApplicationService

class CompleteApplicationUseCaseImpl(
    private val applicationService: ApplicationService
) : CompleteApplicationUseCase {
    override suspend fun invoke(applicationId: Long) =
        applicationService.completeApplication(applicationId)
}
