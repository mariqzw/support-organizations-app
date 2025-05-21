package com.mariqzw.supportorganizationsapp.domain.navigation

import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data object AuthorizationScreen: Route

    @Serializable
    data object RegistrationScreen: Route

    @Serializable
    data object SignUpScreen: Route

    @Serializable
    data object MapScreen: Route

    @Serializable
    data object ApplicationsListScreen: Route

    @Serializable
    data object ApplicationsCompanionListScreen: Route

    @Serializable
    data object AddApplicationsScreen: Route

    @Serializable
    data object ChatsListScreen: Route

    @Serializable
    data object ProfileScreen: Route

    @Serializable
    data object SettingsScreen: Route

    @Serializable
    data class ApplicationCompanionDetailsScreen(
        val id: Long,
        val date: String,
        val time: String,
        val startPoint: String,
        val endPoint: String,
        val status: String,
        val comment: String = ""
    ) : Route

    @Serializable
    data class ApplicationDetailsScreen(
        val id: Long,
        val date: String,
        val time: String,
        val startPoint: String,
        val endPoint: String,
        val status: String,
        val comment: String = ""
    ) : Route

    @Serializable
    data object SplashScreen: Route
}
