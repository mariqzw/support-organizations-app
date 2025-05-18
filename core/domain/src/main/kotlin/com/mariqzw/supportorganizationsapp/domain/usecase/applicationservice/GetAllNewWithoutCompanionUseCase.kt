package com.mariqzw.supportorganizationsapp.domain.usecase.applicationservice

import com.mariqzw.supportorganizationsapp.model.response.ApplicationResponse

interface GetAllNewWithoutCompanionUseCase {
    suspend operator fun invoke(): Result<List<ApplicationResponse>>
}
