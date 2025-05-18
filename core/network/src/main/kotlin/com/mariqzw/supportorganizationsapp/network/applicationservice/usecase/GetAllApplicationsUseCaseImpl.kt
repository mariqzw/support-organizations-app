package com.mariqzw.supportorganizationsapp.network.applicationservice.usecase

import com.mariqzw.supportorganizationsapp.domain.usecase.applicationservice.GetAllApplicationsUseCase
import com.mariqzw.supportorganizationsapp.model.response.ApplicationResponse
import com.mariqzw.supportorganizationsapp.network.applicationservice.ApplicationService
import com.mariqzw.supportorganizationsapp.network.tokenservice.TokenSupport

class GetAllApplicationsUseCaseImpl(
    private val applicationService: ApplicationService,
    private val tokenSupport: TokenSupport
) : GetAllApplicationsUseCase {
    override suspend fun invoke(): Result<List<ApplicationResponse>> =
        tokenSupport.withTokenCheck { token ->
            applicationService.getAllApplications(token = token)
        }
}
