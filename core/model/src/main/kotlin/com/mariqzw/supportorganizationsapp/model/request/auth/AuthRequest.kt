package com.mariqzw.supportorganizationsapp.model.request.auth

import kotlinx.serialization.Serializable

@Serializable
data class AuthRequest (
    val email: String,
    val password: String
)
