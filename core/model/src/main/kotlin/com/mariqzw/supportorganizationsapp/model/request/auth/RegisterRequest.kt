package com.mariqzw.supportorganizationsapp.model.request.auth

import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequest (
    val email: String,
    val phoneNumber: String,
    val firstName: String,
    val lastName: String,
    val password: String
)
