package com.mariqzw.supportorganizationsapp.domain.usecase.userservice

import com.mariqzw.supportorganizationsapp.model.response.UserResponse

interface GetUserByNameUseCase {
    suspend operator fun invoke(name: String) : Result<List<UserResponse>>
}
