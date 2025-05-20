package com.mariqzw.supportorganizationsapp.network.applicationservice.usecase

import com.mariqzw.supportorganizationsapp.domain.usecase.applicationservice.CancelApplicationUseCase
import com.mariqzw.supportorganizationsapp.network.applicationservice.ApplicationService
import com.mariqzw.supportorganizationsapp.network.tokenservice.TokenSupport

class CancelApplicationUseCaseImpl(
    private val applicationService: ApplicationService,
    private val tokenSupport: TokenSupport
) : CancelApplicationUseCase {
    override suspend fun invoke(applicationId: Long) =
        tokenSupport.withTokenCheck { token ->
            applicationService.cancelApplication(token = token, applicationId = applicationId)
        }
}
