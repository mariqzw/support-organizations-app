package com.mariqzw.supportorganizationsapp.model.request.application

import kotlinx.serialization.Serializable

@Serializable
data class UpdateApplicationRequest(
    val date: String,
    val time: String,
    val departureStation: String,
    val destinationStation: String,
    val comment: String
)