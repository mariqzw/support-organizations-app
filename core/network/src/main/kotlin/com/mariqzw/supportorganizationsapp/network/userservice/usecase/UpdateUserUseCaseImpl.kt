package com.mariqzw.supportorganizationsapp.network.userservice.usecase

import com.mariqzw.supportorganizationsapp.domain.usecase.userservice.UpdateUserUseCase
import com.mariqzw.supportorganizationsapp.model.request.UpdateUserRequest
import com.mariqzw.supportorganizationsapp.model.response.UpdateUserResponse
import com.mariqzw.supportorganizationsapp.model.response.UserResponse
import com.mariqzw.supportorganizationsapp.network.tokenservice.TokenSupport
import com.mariqzw.supportorganizationsapp.network.userservice.UserService

class UpdateUserUseCaseImpl(
    private val userService: UserService,
    private val tokenSupport: TokenSupport
) : UpdateUserUseCase {
    override suspend fun invoke(model: UpdateUserRequest): Result<UpdateUserResponse> =
        tokenSupport.withTokenCheck { token ->
            userService.updateUser(token = token, model = model)
        }
}
