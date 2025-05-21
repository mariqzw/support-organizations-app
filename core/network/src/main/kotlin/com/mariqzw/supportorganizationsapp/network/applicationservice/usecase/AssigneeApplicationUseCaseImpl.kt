package com.mariqzw.supportorganizationsapp.network.applicationservice.usecase

import com.mariqzw.supportorganizationsapp.domain.usecase.applicationservice.AssigneeApplicationUseCase
import com.mariqzw.supportorganizationsapp.model.response.ApplicationResponse
import com.mariqzw.supportorganizationsapp.network.applicationservice.ApplicationService
import com.mariqzw.supportorganizationsapp.network.tokenservice.TokenSupport

class AssigneeApplicationUseCaseImpl(
    private val applicationService: ApplicationService,
    private val tokenSupport: TokenSupport
) : AssigneeApplicationUseCase {
    override suspend fun invoke(applicationId: Long): Result<ApplicationResponse> =
        tokenSupport.withTokenCheck { token ->
            applicationService.assigneeApplication(token = token, applicationId = applicationId)
        }
}
