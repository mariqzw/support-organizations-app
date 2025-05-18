package com.mariqzw.supportorganizationsapp.domain.usecase.applicationservice

import com.mariqzw.supportorganizationsapp.model.request.application.UpdateApplicationRequest
import com.mariqzw.supportorganizationsapp.model.response.ApplicationResponse

interface UpdateApplicationUseCase {
    suspend operator fun invoke(applicationId: Long, model: UpdateApplicationRequest): Result<ApplicationResponse>
}
