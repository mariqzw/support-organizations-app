package com.mariqzw.supportorganizationsapp.domain.usecase.applicationservice

import com.mariqzw.supportorganizationsapp.model.response.ApplicationResponse

interface StartApplicationUseCase {
    suspend operator fun invoke(applicationId: Long): Result<ApplicationResponse>
}
