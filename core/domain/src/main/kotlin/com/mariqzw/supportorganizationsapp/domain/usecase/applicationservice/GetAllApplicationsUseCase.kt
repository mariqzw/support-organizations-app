package com.mariqzw.supportorganizationsapp.domain.usecase.applicationservice

import com.mariqzw.supportorganizationsapp.model.response.ApplicationResponse

interface GetAllApplicationsUseCase {
    suspend operator fun invoke(): Result<List<ApplicationResponse>>
}
