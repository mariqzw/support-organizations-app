package com.mariqzw.supportorganizationsapp.network.applicationservice.usecase

import com.mariqzw.supportorganizationsapp.domain.usecase.applicationservice.StartApplicationUseCase
import com.mariqzw.supportorganizationsapp.network.applicationservice.ApplicationService

class StartApplicationUseCaseImpl(
    private val applicationService: ApplicationService
) : StartApplicationUseCase {
    override suspend fun invoke(applicationId: Long) =
        applicationService.startApplication(applicationId)
}
