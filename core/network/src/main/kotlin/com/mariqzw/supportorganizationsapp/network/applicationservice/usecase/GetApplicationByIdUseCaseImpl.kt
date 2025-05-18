package com.mariqzw.supportorganizationsapp.network.applicationservice.usecase

import com.mariqzw.supportorganizationsapp.domain.usecase.applicationservice.GetApplicationByIdUseCase
import com.mariqzw.supportorganizationsapp.network.applicationservice.ApplicationService

class GetApplicationByIdUseCaseImpl(
    private val applicationService: ApplicationService
) : GetApplicationByIdUseCase {
    override suspend fun invoke(applicationId: Long) =
        applicationService.getApplicationById(applicationId)
}
