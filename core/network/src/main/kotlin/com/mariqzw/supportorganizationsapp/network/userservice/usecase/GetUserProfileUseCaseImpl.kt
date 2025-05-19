package com.mariqzw.supportorganizationsapp.network.userservice.usecase

import com.mariqzw.supportorganizationsapp.domain.usecase.userservice.GetUserProfileUseCase
import com.mariqzw.supportorganizationsapp.model.response.UserResponse
import com.mariqzw.supportorganizationsapp.network.tokenservice.TokenSupport
import com.mariqzw.supportorganizationsapp.network.userservice.UserService

class GetUserProfileUseCaseImpl(
    private val userService: UserService,
    private val tokenSupport: TokenSupport
) : GetUserProfileUseCase {
    override suspend fun invoke(): Result<UserResponse> =
        tokenSupport.withTokenCheck { token ->
            userService.getUserProfile(token)
        }
}
