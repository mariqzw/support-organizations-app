package com.mariqzw.supportorganizationsapp.model.response

import kotlinx.serialization.Serializable

@Serializable
data class UpdateUserResponse(
    val message: String,
    val status: Boolean
)
