package com.mariqzw.supportorganizationsapp.network.applicationservice.usecase

import com.mariqzw.supportorganizationsapp.domain.usecase.applicationservice.DeleteApplicationUseCase
import com.mariqzw.supportorganizationsapp.network.applicationservice.ApplicationService
import com.mariqzw.supportorganizationsapp.network.tokenservice.TokenSupport
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DeleteApplicationUseCaseImpl(
    private val applicationService: ApplicationService,
    private val tokenSupport: TokenSupport
) : DeleteApplicationUseCase {
    override suspend fun invoke(applicationId: Long) =
        withContext(Dispatchers.IO) {
            tokenSupport.withTokenCheck { token ->
                runCatching {  applicationService.deleteApplication(token = token, applicationId = applicationId) }
            }
        }
}
