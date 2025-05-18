package com.mariqzw.supportorganizationsapp.model.response

data class AuthDataStore(
    val userId: Long?,
    val accessToken: String?,
    val refreshToken: String?,
    val isAuthenticated: Boolean?
)
