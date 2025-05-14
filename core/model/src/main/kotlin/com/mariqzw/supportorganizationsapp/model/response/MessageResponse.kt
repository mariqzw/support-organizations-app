package com.mariqzw.supportorganizationsapp.model.response

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class MessageResponse(
    val content: String,
    @Contextual val timeStamp: LocalDateTime,
)