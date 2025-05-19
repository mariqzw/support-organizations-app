package com.mariqzw.supportorganizationsapp.domain.usecase.userservice

import com.mariqzw.supportorganizationsapp.domain.usecase.UseCase
import com.mariqzw.supportorganizationsapp.model.request.UpdateUserRequest
import com.mariqzw.supportorganizationsapp.model.response.UpdateUserResponse
import com.mariqzw.supportorganizationsapp.model.response.UserResponse

interface UpdateUserUseCase : UseCase {
    suspend operator fun invoke(model: UpdateUserRequest) : Result<UpdateUserResponse>
}
