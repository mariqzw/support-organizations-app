package com.mariqzw.supportorganizationsapp.domain.usecase.userservice

import com.mariqzw.supportorganizationsapp.model.response.UserResponse

interface GetUserProfileUseCase {
    suspend operator fun invoke(token: String): Result<UserResponse>
}
