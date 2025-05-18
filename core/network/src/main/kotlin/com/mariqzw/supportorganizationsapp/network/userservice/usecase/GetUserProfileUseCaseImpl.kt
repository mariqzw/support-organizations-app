package com.mariqzw.supportorganizationsapp.network.userservice.usecase

import com.mariqzw.supportorganizationsapp.domain.usecase.userservice.GetUserProfileUseCase
import com.mariqzw.supportorganizationsapp.model.response.UserResponse
import com.mariqzw.supportorganizationsapp.network.userservice.UserService

class GetUserProfileUseCaseImpl(
    private val userService: UserService
) : GetUserProfileUseCase {
    override suspend fun invoke(token: String): Result<UserResponse> =
        userService.getUserProfile(token)
}
