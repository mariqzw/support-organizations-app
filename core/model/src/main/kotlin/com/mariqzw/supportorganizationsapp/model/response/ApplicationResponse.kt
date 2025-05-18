package com.mariqzw.supportorganizationsapp.model.response

import kotlinx.serialization.Serializable

@Serializable
data class ApplicationResponse(
    val id: Long,
    val date: String,
    val time: String,
    val departureStation: String,
    val destinationStation: String,
    val comment: String,
    val status: String,
    val passengerId: Long,
    val companionId: Long?
)
