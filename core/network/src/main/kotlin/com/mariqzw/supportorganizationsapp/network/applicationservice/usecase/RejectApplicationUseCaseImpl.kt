package com.mariqzw.supportorganizationsapp.network.applicationservice.usecase

import com.mariqzw.supportorganizationsapp.domain.usecase.applicationservice.RejectApplicationUseCase
import com.mariqzw.supportorganizationsapp.model.response.ApplicationResponse
import com.mariqzw.supportorganizationsapp.network.applicationservice.ApplicationService
import com.mariqzw.supportorganizationsapp.network.tokenservice.TokenSupport

class RejectApplicationUseCaseImpl(
    private val applicationService: ApplicationService,
    private val tokenSupport: TokenSupport
) : RejectApplicationUseCase {
    override suspend fun invoke(applicationId: Long) =
        tokenSupport.withTokenCheck { token ->
            applicationService.rejectApplication(token = token, applicationId = applicationId)
        }
}
