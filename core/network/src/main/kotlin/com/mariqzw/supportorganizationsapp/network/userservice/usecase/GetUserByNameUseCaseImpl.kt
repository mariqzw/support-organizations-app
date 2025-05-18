package com.mariqzw.supportorganizationsapp.network.userservice.usecase

import com.mariqzw.supportorganizationsapp.domain.usecase.userservice.GetUserByNameUseCase
import com.mariqzw.supportorganizationsapp.model.response.UserResponse
import com.mariqzw.supportorganizationsapp.network.userservice.UserService

class GetUserByNameUseCaseImpl(
    private val userService: UserService
) : GetUserByNameUseCase {
    override suspend fun invoke(name: String): Result<List<UserResponse>> =
        userService.getUsersByName(name)
}
