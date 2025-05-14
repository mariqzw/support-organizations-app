package com.mariqzw.supportorganizationsapp.model.request

import kotlinx.serialization.Serializable

@Serializable
data class UpdateUserRequest(
    val email: String,
    val phoneNumber: String,
    val firstName: String,
    val lastName: String
)
