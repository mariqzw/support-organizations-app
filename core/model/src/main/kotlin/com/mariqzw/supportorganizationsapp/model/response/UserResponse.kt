package com.mariqzw.supportorganizationsapp.model.response

import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val email: String,
    val phoneNumber: String,
    val firstName: String,
    val lastName: String
)