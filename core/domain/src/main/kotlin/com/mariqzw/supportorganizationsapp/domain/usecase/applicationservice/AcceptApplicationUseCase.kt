package com.mariqzw.supportorganizationsapp.domain.usecase.applicationservice

import com.mariqzw.supportorganizationsapp.model.response.ApplicationResponse

interface AcceptApplicationUseCase {
    suspend operator fun invoke(applicationId: Long, companionId: Long): Result<ApplicationResponse>
}
