package com.mariqzw.supportorganizationsapp.network.authservice

import com.mariqzw.supportorganizationsapp.data.auth.AuthenticationDataStore
import com.mariqzw.supportorganizationsapp.network.extensions.request
import com.mariqzw.supportorganizationsapp.model.request.auth.AuthRequest
import com.mariqzw.supportorganizationsapp.model.request.auth.RefreshToken
import com.mariqzw.supportorganizationsapp.model.request.auth.RegisterRequest
import com.mariqzw.supportorganizationsapp.model.response.AuthDataStore
import com.mariqzw.supportorganizationsapp.model.response.AuthNetworkResponse
import com.mariqzw.supportorganizationsapp.model.response.asUserAuthDataStore
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.http.path
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

interface AuthService {

    suspend fun signIn(model: AuthRequest): Result<AuthNetworkResponse>

    suspend fun register(model: RegisterRequest): Result<AuthNetworkResponse>

    suspend fun registerCompanion(model: RegisterRequest): Result<AuthNetworkResponse>

    suspend fun refreshToken(model: RefreshToken): Result<AuthNetworkResponse>
}

class KtorAuthService(
    private val client: HttpClient,
    private val apiHost: String,
    private val dispatcher: CoroutineDispatcher,
    private val authenticationDataStore: AuthenticationDataStore
) : AuthService {
    override suspend fun signIn(model: AuthRequest) = withContext(dispatcher) {
        client.request<AuthNetworkResponse> {
            post {
                url {
                    protocol = URLProtocol.HTTP
                    host = apiHost
                    port = 8080
                    path("auth", "signin")
                    contentType(ContentType.Application.Json)
                }
                setBody(model)
            }
        }.also { response ->
            response.onSuccess { authNetworkResponse ->
                authenticationDataStore.updateUser(authNetworkResponse.asUserAuthDataStore())
            }
        }
    }


    override suspend fun register(model: RegisterRequest) = withContext(dispatcher) {
        client.request<AuthNetworkResponse> {
            post {
                url {
                    protocol = URLProtocol.HTTP
                    host = apiHost
                    port = 8080
                    path("auth", "signup", "passenger")
                    contentType(ContentType.Application.Json)
                }
                setBody(model)
            }
        }.also { response ->
            response.onSuccess { authNetworkResponse ->
                authenticationDataStore.updateUser(authNetworkResponse.asUserAuthDataStore())
            }
        }
    }

    override suspend fun registerCompanion(model: RegisterRequest) = withContext(dispatcher) {
        client.request<AuthNetworkResponse> {
            post {
                url {
                    protocol = URLProtocol.HTTP
                    host = apiHost
                    port = 8080
                    path("auth", "signup", "companion")
                    contentType(ContentType.Application.Json)
                }
                setBody(model)
            }
        }.also { response ->
            response.onSuccess { authNetworkResponse ->
                authenticationDataStore.updateUser(authNetworkResponse.asUserAuthDataStore())
            }
        }
    }

    override suspend fun refreshToken(model: RefreshToken) = withContext(dispatcher) {
        client.request<AuthNetworkResponse> {
            post {
                url {
                    protocol = URLProtocol.HTTP
                    host = apiHost
                    port = 8080
                    path("auth", "refresh")
                    contentType(ContentType.Application.Json)
                }
                setBody(model)
            }
        }.also { response ->
            response.onSuccess { authNetworkResponse ->
                authNetworkResponse.asUserAuthDataStore().let { updatedData ->
                    authenticationDataStore.updateUser(
                        AuthDataStore(
                            userId = updatedData.userId,
                            accessToken = updatedData.accessToken,
                            refreshToken = updatedData.refreshToken,
                            isAuthenticated = updatedData.isAuthenticated
                        )
                    )
                }
            }
        }
    }
}
