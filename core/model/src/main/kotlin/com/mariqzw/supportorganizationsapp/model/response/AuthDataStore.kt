package com.mariqzw.supportorganizationsapp.model.response

data class AuthDataStore(
    val id: Long?,
    val accessToken: String?,
    val refreshToken: String?,
    val accessTokenExpiresIn: Int?,
    val refreshTokenExpiresIn: Int?
)
