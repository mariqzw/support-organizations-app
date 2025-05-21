package com.mariqzw.supportorganizationsapp.ui.presentation.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mariqzw.supportorganizationsapp.applications.presentation.screens.ApplicationCompanionDetailsScreen
import com.mariqzw.supportorganizationsapp.applications.presentation.screens.ApplicationsCompanionListScreen
import com.mariqzw.supportorganizationsapp.auth.presentation.screens.AuthorizationScreen
import com.mariqzw.supportorganizationsapp.auth.presentation.screens.SignUpScreen
import com.mariqzw.supportorganizationsapp.auth.presentation.screens.SplashScreen
import com.mariqzw.supportorganizationsapp.chats.presentation.screens.ChatsListScreen
import com.mariqzw.supportorganizationsapp.domain.navigation.Route
import com.mariqzw.supportorganizationsapp.mapmetro.presentation.screens.MapScreen
import com.mariqzw.supportorganizationsapp.profile.presentation.screens.ProfileScreen
import com.mariqzw.supportorganizationsapp.profile.presentation.screens.SettingsScreen
import com.mariqzw.supportorganizationsapp.ui.components.navigation.BottomNavBarCompanion
import com.mariqzw.supportorganizationsapp.ui.components.navigation.BottomNavItem
import com.mariqzw.supportorganizationsapp.ui.components.navigation.TopBar
import com.mariqzw.supportorganizationsapp.ui.presentation.viewmodel.MainActivityViewModel
import com.mariqzw.supportorganizationsapp.ui.presentation.viewmodel.UiScaffoldState
import com.mariqzw.supportorganizationsapp.ui.provider.LoсalSnackbarHostState
import com.mariqzw.supportorganizationsapp.ui.theme.SupportOrganizationsAppTheme
import com.mariqzw.supportorganizationsapp.ui.theme.backgroundLight
import org.koin.android.ext.android.inject
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {

    private val bottomNavItems: List<BottomNavItem> by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SupportOrganizationsAppTheme {
                val coroutineScope = rememberCoroutineScope()
                val navController = rememberNavController()
                val snackbarHostState = remember { SnackbarHostState() }

                val viewModel: MainActivityViewModel = koinViewModel()
                val scaffoldState by viewModel.scaffoldState.collectAsState()

                val navBackStackEntry = navController.currentBackStackEntryAsState().value
                val currentRoute = navBackStackEntry?.destination?.route

                val sliderValue = remember { mutableFloatStateOf(0.5f) }
                val isChecked = remember { mutableStateOf(true) }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    snackbarHost = { SnackbarHost(snackbarHostState) },
                    topBar = {
                        when (scaffoldState.appBarState) {
                            is UiScaffoldState.AppBarState.None -> Unit
                            is UiScaffoldState.AppBarState.Toolbar -> {
                                val toolbar = scaffoldState.appBarState as UiScaffoldState.AppBarState.Toolbar
                                TopBar(
                                    modifier = Modifier.statusBarsPadding(),
                                    title = toolbar.screenName,
                                    showArrow = toolbar.canNavigateBack,
                                    onClick = {
                                        if (toolbar.canNavigateBack) navController.popBackStack()
                                    }
                                )
                            }
                        }
                    },
                    containerColor = backgroundLight,
                    bottomBar = {
                        if (scaffoldState.showBottomBar) {
                            BottomNavBarCompanion(
                                navController = navController,
                                scope = coroutineScope,
                                navItems = bottomNavItems
                            )
                        }
                    }
                ) { innerPadding ->
                    CompositionLocalProvider(LoсalSnackbarHostState provides snackbarHostState) {
                        Box(modifier = Modifier.padding(innerPadding)) {
                            NavHost(
                                navController = navController,
                                startDestination = Route.SplashScreen
                            ) {
                                composable<Route.SplashScreen> {
                                    SplashScreen(
                                        navController = navController
                                    )
                                }
                                composable<Route.AuthorizationScreen> {
                                    AuthorizationScreen(
                                        navController = navController,
                                        onRegisterClick = { navController.navigate(Route.SignUpScreen) }
                                    )
                                }
                                composable<Route.SignUpScreen> {
                                    SignUpScreen(
                                        onLoginClick = {
                                            navController.navigate(Route.AuthorizationScreen)
                                        },
                                        navController = navController
                                    )
                                }
                                composable<Route.MapScreen> {
                                    MapScreen()
                                }
                                composable<Route.ApplicationsCompanionListScreen> {
                                    ApplicationsCompanionListScreen(
                                        navController
                                    )
                                }
                                composable<Route.ApplicationCompanionDetailsScreen> { backStackEntry ->
                                    ApplicationCompanionDetailsScreen(
                                        navBackStackEntry = backStackEntry
                                    )
                                }
                                composable<Route.ChatsListScreen> {
                                    ChatsListScreen()
                                }
                                composable<Route.ProfileScreen> {
                                    ProfileScreen(
                                        onSettingsClick = {
                                            navController.navigate(Route.SettingsScreen)
                                        }
                                    )
                                }
                                composable<Route.SettingsScreen> {
                                    SettingsScreen(
                                        sliderValue = sliderValue.value,
                                        onSliderValueChange = { newValue ->
                                            sliderValue.value = newValue
                                        },
                                        checked = isChecked.value,
                                        onCheckedChange = { newChecked ->
                                            isChecked.value = newChecked
                                        },
                                        onLogoutClick = {
                                            viewModel.logout(
                                                onComplete = {
                                                    navController.navigate(Route.AuthorizationScreen)
                                                }
                                            )
                                        }
                                    )
                                }
                            }
                        }
                    }
                }

                LaunchedEffect(currentRoute) {
                    viewModel.updateForRoute(currentRoute)
                }
            }
        }
    }
}
