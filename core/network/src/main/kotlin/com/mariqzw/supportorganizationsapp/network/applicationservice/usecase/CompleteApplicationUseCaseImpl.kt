package com.mariqzw.supportorganizationsapp.network.applicationservice.usecase

import com.mariqzw.supportorganizationsapp.domain.usecase.applicationservice.CompleteApplicationUseCase
import com.mariqzw.supportorganizationsapp.network.applicationservice.ApplicationService
import com.mariqzw.supportorganizationsapp.network.tokenservice.TokenSupport

class CompleteApplicationUseCaseImpl(
    private val applicationService: ApplicationService,
    private val tokenSupport: TokenSupport
) : CompleteApplicationUseCase {
    override suspend fun invoke(applicationId: Long) =
        tokenSupport.withTokenCheck { token ->
            applicationService.completeApplication(token = token, applicationId = applicationId)
        }

}
