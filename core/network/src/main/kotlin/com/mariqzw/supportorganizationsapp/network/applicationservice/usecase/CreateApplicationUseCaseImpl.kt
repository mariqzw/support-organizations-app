package com.mariqzw.supportorganizationsapp.network.applicationservice.usecase

import com.mariqzw.supportorganizationsapp.domain.usecase.applicationservice.CreateApplicationUseCase
import com.mariqzw.supportorganizationsapp.model.request.application.CreateApplicationRequest
import com.mariqzw.supportorganizationsapp.network.applicationservice.ApplicationService
import com.mariqzw.supportorganizationsapp.network.tokenservice.TokenSupport

class CreateApplicationUseCaseImpl(
    private val applicationService: ApplicationService,
    private val tokenSupport: TokenSupport
) : CreateApplicationUseCase {
    override suspend fun invoke(model: CreateApplicationRequest) =
        tokenSupport.withTokenCheck { token ->
            applicationService.createApplication(token = token, model = model)
        }
}
