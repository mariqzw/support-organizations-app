package com.mariqzw.supportorganizationsapp.network.applicationservice.usecase

import com.mariqzw.supportorganizationsapp.domain.usecase.applicationservice.GetAllApplicationsByCompanionUseCase
import com.mariqzw.supportorganizationsapp.model.response.ApplicationResponse
import com.mariqzw.supportorganizationsapp.network.applicationservice.ApplicationService
import com.mariqzw.supportorganizationsapp.network.tokenservice.TokenSupport

class GetAllApplicationsByCompanionUseCaseImpl(
    private val applicationService: ApplicationService,
    private val tokenSupport: TokenSupport
) : GetAllApplicationsByCompanionUseCase {
    override suspend fun invoke(): Result<List<ApplicationResponse>> =
        tokenSupport.withTokenCheck { token ->
            applicationService.getAllByCompanion(token = token)
        }
}
