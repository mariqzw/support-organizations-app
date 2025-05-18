package com.mariqzw.supportorganizationsapp.network.applicationservice.usecase

import com.mariqzw.supportorganizationsapp.domain.usecase.applicationservice.CancelApplicationUseCase
import com.mariqzw.supportorganizationsapp.network.applicationservice.ApplicationService

class CancelApplicationUseCaseImpl(
    private val applicationService: ApplicationService
) : CancelApplicationUseCase {
    override suspend fun invoke(applicationId: Long) =
        applicationService.cancelApplication(applicationId)
}
