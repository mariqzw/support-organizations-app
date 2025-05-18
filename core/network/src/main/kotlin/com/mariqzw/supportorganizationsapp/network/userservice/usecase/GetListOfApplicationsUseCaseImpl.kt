package com.mariqzw.supportorganizationsapp.network.userservice.usecase

import com.mariqzw.supportorganizationsapp.domain.usecase.userservice.GetListOfApplicationsUseCase
import com.mariqzw.supportorganizationsapp.model.response.ApplicationResponse
import com.mariqzw.supportorganizationsapp.network.userservice.UserService

class GetListOfApplicationsUseCaseImpl(
    private val userService: UserService
) : GetListOfApplicationsUseCase {
    override suspend fun invoke(userId: Long): Result<List<ApplicationResponse>> =
        userService.getListOfApplications(userId)
}
