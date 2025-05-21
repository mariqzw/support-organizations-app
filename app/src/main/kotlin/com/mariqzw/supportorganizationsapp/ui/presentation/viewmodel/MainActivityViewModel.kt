package com.mariqzw.supportorganizationsapp.ui.presentation.viewmodel

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mariqzw.supportorganizationsapp.data.auth.AuthenticationDataStore
import com.mariqzw.supportorganizationsapp.ui.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainActivityViewModel(
    private val authDataStore: AuthenticationDataStore,
) : ViewModel() {

    private val _scaffoldState = MutableStateFlow(UiScaffoldState())
    val scaffoldState: StateFlow<UiScaffoldState> = _scaffoldState.asStateFlow()

    fun updateForRoute(currentRoute: String?) {
        _scaffoldState.value = _scaffoldState.value.onRouteChanged(currentRoute)
    }

    fun logout(onComplete: () -> Unit) {
        viewModelScope.launch {
            authDataStore.clear()
            onComplete()
        }
    }
}

@Immutable
data class UiScaffoldState(
    val appBarState: AppBarState = AppBarState.None,
    val showBottomBar: Boolean = false
) {

    sealed interface AppBarState {
        data object None : AppBarState
        data class Toolbar(
            val screenName: String,
            val isMainScreen: Boolean,
            val canNavigateBack: Boolean,
            val iconLeft: Int?,
            val iconRight: Int?,
            val onRightIconClick: (navController: androidx.navigation.NavHostController) -> () -> Unit = { { } }
        ) : AppBarState
    }

    fun onRouteChanged(currentRoute: String?): UiScaffoldState {
        if (currentRoute == null) return this

        val showBottomNavBar = currentRoute.showBottomNavBar()
        val appBarState = calculateAppBarState(currentRoute)

        return copy(
            appBarState = appBarState,
            showBottomBar = showBottomNavBar
        )
    }

    private fun calculateAppBarState(route: String): AppBarState {

        return when (route.routeSuffix()) {
            "Map" -> AppBarState.Toolbar(
                screenName = "Карта",
                isMainScreen = true,
                canNavigateBack = false,
                iconLeft = null,
                iconRight = null
            )

            "ApplicationsList" -> AppBarState.Toolbar(
                screenName = "Заявки",
                isMainScreen = true,
                canNavigateBack = false,
                iconLeft = null,
                iconRight = null
            )

            "AddApplications" -> AppBarState.Toolbar(
                screenName = "Добавить заявку",
                isMainScreen = false,
                canNavigateBack = false,
                iconLeft = null,
                iconRight = null
            )

            "ChatsList" -> AppBarState.Toolbar(
                screenName = "Чаты",
                isMainScreen = true,
                canNavigateBack = false,
                iconLeft = null,
                iconRight = null
            )

            "Profile" -> AppBarState.Toolbar(
                screenName = "Профиль",
                isMainScreen = true,
                canNavigateBack = false,
                iconLeft = null,
                iconRight = null
            )

            "Settings" -> AppBarState.Toolbar(
                screenName = "Настройки",
                isMainScreen = true,
                canNavigateBack = true,
                iconLeft = R.drawable.arrow_back,
                iconRight = null
            )

            "ApplicationDetails" -> AppBarState.Toolbar(
                screenName = "Информация о заявке",
                isMainScreen = true,
                canNavigateBack = true,
                iconLeft = R.drawable.arrow_back,
                iconRight = null
            )

            else -> AppBarState.None
        }
    }
}

private fun String.routeSuffix(): String {
    return this
        .substringAfterLast('.')
        .substringBefore('/')
        .removeSuffix("Screen")
}

private fun String.showBottomNavBar(): Boolean =
    routeSuffix() in setOf("Map", "ApplicationsList", "AddApplications", "ChatsList", "Profile")
