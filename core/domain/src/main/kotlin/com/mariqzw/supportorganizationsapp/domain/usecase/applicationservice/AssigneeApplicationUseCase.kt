package com.mariqzw.supportorganizationsapp.domain.usecase.applicationservice

import com.mariqzw.supportorganizationsapp.model.response.ApplicationResponse

interface AssigneeApplicationUseCase {
    suspend operator fun invoke(applicationId: Long) : Result<ApplicationResponse>
}
