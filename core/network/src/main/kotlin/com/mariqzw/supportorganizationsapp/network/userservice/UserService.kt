package com.mariqzw.supportorganizationsapp.network.userservice

import com.mariqzw.supportorganizationsapp.model.request.UpdateUserRequest
import com.mariqzw.supportorganizationsapp.model.request.application.CreateApplicationRequest
import com.mariqzw.supportorganizationsapp.model.response.ApplicationResponse
import com.mariqzw.supportorganizationsapp.model.response.UpdateUserResponse
import com.mariqzw.supportorganizationsapp.model.response.UserResponse
import com.mariqzw.supportorganizationsapp.network.extensions.request
import io.ktor.client.HttpClient
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.http.path
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

interface UserService {

    suspend fun updateUser(token: String, model: UpdateUserRequest): Result<UpdateUserResponse>

    suspend fun createApplications(model: CreateApplicationRequest): Result<ApplicationResponse>

    suspend fun cancelApplications(applicationId: Long): Result<ApplicationResponse>

    suspend fun getListOfApplications(userId: Long): Result<List<ApplicationResponse>>

    suspend fun getUsersByName(name: String): Result<List<UserResponse>>

    suspend fun getUserProfile(token: String): Result<UserResponse>

}

class KtorUserService(
    private val client: HttpClient,
    private val apiHost: String,
    private val dispatcher: CoroutineDispatcher
) : UserService {
    override suspend fun updateUser(token: String, model: UpdateUserRequest) = withContext(dispatcher) {
        client.request<UpdateUserResponse> {
            put {
                url {
                    protocol = URLProtocol.HTTP
                    host = apiHost
                    port = 8080
                    path("api", "users", "update")
                    contentType(ContentType.Application.Json)
                }
                bearerAuth(token)
                setBody(model)
            }
        }
    }

    override suspend fun createApplications(model: CreateApplicationRequest) = withContext(dispatcher) {
        client.request<ApplicationResponse> {
            post {
                url {
                    protocol = URLProtocol.HTTP
                    host = apiHost
                    port = 8080
                    path("api", "users", "applications")
                    contentType(ContentType.Application.Json)
                }
                setBody(model)
            }
        }
    }

    override suspend fun cancelApplications(applicationId: Long) = withContext(dispatcher) {
        client.request<ApplicationResponse> {
            post {
                url {
                    protocol = URLProtocol.HTTP
                    host = apiHost
                    port = 8080
                    path("api", "users", "applications", applicationId.toString(), "cancel")
                    contentType(ContentType.Application.Json)
                }
            }
        }
    }

    override suspend fun getListOfApplications(userId: Long) = withContext(dispatcher) {
        client.request<List<ApplicationResponse>> {
            get {
                url {
                    protocol = URLProtocol.HTTP
                    host = apiHost
                    port = 8080
                    path("api", "users", userId.toString(), "applications")
                }
            }
        }
    }

    override suspend fun getUsersByName(name: String) = withContext(dispatcher) {
        client.request<List<UserResponse>> {
            get {
                url {
                    protocol = URLProtocol.HTTP
                    host = apiHost
                    port = 8080
                    path("api", "users", "search")
                    parameters.append("name", name)
                }
                setBody(name)
            }
        }
    }

    override suspend fun getUserProfile(token: String) = withContext(dispatcher) {
        client.request<UserResponse> {
            get {
                url {
                    protocol = URLProtocol.HTTP
                    host = apiHost
                    port = 8080
                    path("api", "users", "profile")
                }
                header("Authorization", "Bearer $token")
            }
        }
    }
}
