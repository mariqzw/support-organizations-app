package com.mariqzw.supportorganizationsapp.network.userservice.usecase

import com.mariqzw.supportorganizationsapp.domain.usecase.userservice.CreateApplicationsUseCase
import com.mariqzw.supportorganizationsapp.model.request.application.CreateApplicationRequest
import com.mariqzw.supportorganizationsapp.model.response.ApplicationResponse
import com.mariqzw.supportorganizationsapp.network.userservice.UserService

class CreateApplicationsUseCaseImpl(
    private val userService: UserService
) : CreateApplicationsUseCase {
    override suspend fun invoke(model: CreateApplicationRequest): Result<ApplicationResponse> =
        userService.createApplications(model)
}
