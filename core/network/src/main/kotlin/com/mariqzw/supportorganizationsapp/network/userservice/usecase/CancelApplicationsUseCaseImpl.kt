package com.mariqzw.supportorganizationsapp.network.userservice.usecase

import com.mariqzw.supportorganizationsapp.domain.usecase.userservice.CancelApplicationsUseCase
import com.mariqzw.supportorganizationsapp.model.response.ApplicationResponse
import com.mariqzw.supportorganizationsapp.network.userservice.UserService

class CancelApplicationsUseCaseImpl(
    private val userService: UserService
) : CancelApplicationsUseCase {
    override suspend fun invoke(applicationId: Long): Result<ApplicationResponse> =
        userService.cancelApplications(applicationId)
}
