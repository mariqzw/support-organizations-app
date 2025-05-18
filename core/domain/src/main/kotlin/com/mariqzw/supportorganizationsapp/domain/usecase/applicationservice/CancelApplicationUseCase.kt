package com.mariqzw.supportorganizationsapp.domain.usecase.applicationservice

import com.mariqzw.supportorganizationsapp.model.response.ApplicationResponse

interface CancelApplicationUseCase {
    suspend operator fun invoke(applicationId: Long): Result<ApplicationResponse>
}
