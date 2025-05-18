package com.mariqzw.supportorganizationsapp.domain.usecase.applicationservice

interface DeleteApplicationUseCase {
    suspend operator fun invoke(applicationId: Long)
}
