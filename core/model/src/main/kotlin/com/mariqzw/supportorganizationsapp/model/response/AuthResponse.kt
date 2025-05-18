package com.mariqzw.supportorganizationsapp.model.response

import kotlinx.serialization.Serializable

@Serializable
data class AuthNetworkResponse(
    val userId: Long,
    val accessToken: String,
    val refreshToken: String,
    val isAuthenticated: Boolean
)

/**
 * Преобразует [AuthNetworkResponse] объект в [AuthDataStore] объект
 */
fun AuthNetworkResponse.asUserAuthDataStore(): AuthDataStore = with(this) {
    AuthDataStore(
        userId = userId,
        accessToken = accessToken,
        refreshToken = refreshToken,
        isAuthenticated = isAuthenticated
    )
}
