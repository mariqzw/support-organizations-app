package com.mariqzw.supportorganizationsapp.model.request

import kotlinx.serialization.Serializable

@Serializable
data class SendMessageRequest(
    val content: String
)
