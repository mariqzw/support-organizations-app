package com.mariqzw.supportorganizationsapp.network.userservice.usecase

import com.mariqzw.supportorganizationsapp.domain.usecase.userservice.UpdateUserUseCase
import com.mariqzw.supportorganizationsapp.model.request.UpdateUserRequest
import com.mariqzw.supportorganizationsapp.model.response.UserResponse
import com.mariqzw.supportorganizationsapp.network.userservice.UserService

class UpdateUserUseCaseImpl(
    private val userService: UserService
) : UpdateUserUseCase {
    override suspend fun invoke(token: String, model: UpdateUserRequest): Result<UserResponse> =
        userService.updateUser(token, model)
}
