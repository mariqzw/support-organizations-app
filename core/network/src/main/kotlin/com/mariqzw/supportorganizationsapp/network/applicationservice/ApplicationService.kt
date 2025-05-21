package com.mariqzw.supportorganizationsapp.network.applicationservice

import com.mariqzw.supportorganizationsapp.model.request.application.CreateApplicationRequest
import com.mariqzw.supportorganizationsapp.model.request.application.UpdateApplicationRequest
import com.mariqzw.supportorganizationsapp.model.response.ApplicationResponse
import com.mariqzw.supportorganizationsapp.network.extensions.request
import io.ktor.client.HttpClient
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.http.path
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

interface ApplicationService {

    suspend fun getApplicationById(applicationId: Long) : Result<ApplicationResponse>

    suspend fun updateApplication(applicationId: Long, model: UpdateApplicationRequest) : Result<ApplicationResponse>

    suspend fun deleteApplication(token: String, applicationId: Long) : Boolean

    suspend fun getAllApplications(token: String) : Result<List<ApplicationResponse>>

    suspend fun getAllByCompanion(token: String) : Result<List<ApplicationResponse>>

    suspend fun getAllNewWithoutCompanion(token: String) : Result<List<ApplicationResponse>>

    suspend fun createApplication(token: String, model: CreateApplicationRequest) : Result<ApplicationResponse>

    suspend fun startApplication(applicationId: Long) : Result<ApplicationResponse>

    suspend fun rejectApplication(applicationId: Long) : Result<ApplicationResponse>

    suspend fun completeApplication(applicationId: Long) : Result<ApplicationResponse>

    suspend fun cancelApplication(token: String, applicationId: Long) : Result<ApplicationResponse>

    suspend fun acceptApplication(applicationId: Long, companionId: Long) : Result<ApplicationResponse>

}

class KtorApplicationService(
    private val client: HttpClient,
    private val apiHost: String,
    private val dispatcher: CoroutineDispatcher
) : ApplicationService {
    override suspend fun getApplicationById(applicationId: Long) = withContext(dispatcher) {
        client.request<ApplicationResponse> {
            get {
                url {
                    protocol = URLProtocol.HTTP
                    host = apiHost
                    port = 8080
                    path("applications", applicationId.toString())
                }
                setBody(applicationId)
            }
        }
    }

    override suspend fun updateApplication(applicationId: Long, model: UpdateApplicationRequest) = withContext(dispatcher) {
        client.request<ApplicationResponse> {
            put {
                url {
                    protocol = URLProtocol.HTTP
                    host = apiHost
                    port = 8080
                    path("applications", applicationId.toString())
                    contentType(ContentType.Application.Json)
                }
                setBody(model)
            }
        }
    }

    override suspend fun deleteApplication(token: String, applicationId: Long) = withContext(dispatcher) {
        val response: HttpResponse = client.delete {
            url {
                protocol = URLProtocol.HTTP
                host = apiHost
                port = 8080
                path("applications", applicationId.toString())
            }
            bearerAuth(token = token)
        }
        try {
            response.body<Boolean>()
        } catch (e: NoTransformationFoundException) {
            if (response.status == HttpStatusCode.NoContent) true
            else throw e
        }
    }

    override suspend fun getAllApplications(token: String) = withContext(dispatcher) {
        client.request<List<ApplicationResponse>> {
            get {
                url {
                    protocol = URLProtocol.HTTP
                    host = apiHost
                    port = 8080
                    path("applications", "passenger")
                }
                bearerAuth(token = token)
            }
        }
    }

    override suspend fun getAllByCompanion(token: String) = withContext(dispatcher) {
        client.request<List<ApplicationResponse>> {
            get {
                url {
                    protocol = URLProtocol.HTTP
                    host = apiHost
                    port = 8080
                    path("applications", "companion")
                }
                bearerAuth(token = token)
            }
        }
    }

    override suspend fun getAllNewWithoutCompanion(token: String) = withContext(dispatcher) {
        client.request<List<ApplicationResponse>> {
            get {
                url {
                    protocol = URLProtocol.HTTP
                    host = apiHost
                    port = 8080
                    path("applications")
                }
                bearerAuth(token = token)
            }
        }
    }

    override suspend fun createApplication(token: String, model: CreateApplicationRequest) = withContext(dispatcher) {
        client.request<ApplicationResponse> {
            post {
                url {
                    protocol = URLProtocol.HTTP
                    host = apiHost
                    port = 8080
                    path("applications")
                    contentType(ContentType.Application.Json)
                }
                bearerAuth(token = token)
                setBody(model)
            }
        }
    }

    override suspend fun startApplication(applicationId: Long) = withContext(dispatcher) {
        client.request<ApplicationResponse> {
            post {
                url {
                    protocol = URLProtocol.HTTP
                    host = apiHost
                    port = 8080
                    path("applications", applicationId.toString(), "start")
                }
            }
        }
    }

    override suspend fun rejectApplication(applicationId: Long) = withContext(dispatcher) {
        client.request<ApplicationResponse> {
            post {
                url {
                    protocol = URLProtocol.HTTP
                    host = apiHost
                    port = 8080
                    path("applications", applicationId.toString(), "reject")
                }
            }
        }
    }

    override suspend fun completeApplication(applicationId: Long) = withContext(dispatcher) {
        client.request<ApplicationResponse> {
            post {
                url {
                    protocol = URLProtocol.HTTP
                    host = apiHost
                    port = 8080
                    path("applications", applicationId.toString(), "complete")
                }
            }
        }
    }

    override suspend fun cancelApplication(token: String, applicationId: Long) = withContext(dispatcher) {
        client.request<ApplicationResponse> {
            post {
                url {
                    protocol = URLProtocol.HTTP
                    host = apiHost
                    port = 8080
                    path("applications", applicationId.toString(), "cancel")
                }
                bearerAuth(token = token)
            }
        }
    }

    override suspend fun acceptApplication(applicationId: Long, companionId: Long) = withContext(dispatcher) {
        client.request<ApplicationResponse> {
            post {
                url {
                    protocol = URLProtocol.HTTP
                    host = apiHost
                    port = 8080
                    path("applications", applicationId.toString(), "accept")
                    parameter("companionId", companionId.toString())
                }
            }
        }
    }
}
