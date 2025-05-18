package com.mariqzw.supportorganizationsapp.domain.usecase.userservice

import com.mariqzw.supportorganizationsapp.model.response.ApplicationResponse

interface GetListOfApplicationsUseCase {
    suspend operator fun invoke(userId: Long) : Result<List<ApplicationResponse>>
}
