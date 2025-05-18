package com.mariqzw.supportorganizationsapp.domain.usecase.userservice

import com.mariqzw.supportorganizationsapp.model.response.ApplicationResponse

interface CancelApplicationsUseCase {
    suspend operator fun invoke(applicationId: Long) : Result<ApplicationResponse>
}
