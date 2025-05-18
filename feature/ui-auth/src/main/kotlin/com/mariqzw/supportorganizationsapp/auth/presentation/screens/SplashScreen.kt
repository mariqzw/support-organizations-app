package com.mariqzw.supportorganizationsapp.auth.presentation.screens

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavController
import com.mariqzw.supportorganizationsapp.auth.presentation.viewmodels.SplashScreenState
import com.mariqzw.supportorganizationsapp.auth.presentation.viewmodels.SplashScreenViewModel
import com.mariqzw.supportorganizationsapp.domain.navigation.Route
import com.mariqzw.supportorganizationsapp.ui.R
import com.mariqzw.supportorganizationsapp.ui.theme.LocalDimensions
import com.mariqzw.supportorganizationsapp.ui.theme.backgroundLight
import org.koin.androidx.compose.koinViewModel

@Composable
fun SplashScreen(
    navController: NavController,
    viewModel: SplashScreenViewModel = koinViewModel()
) {
    val dimensions = LocalDimensions.current
    val state by viewModel.state.collectAsState(initial = SplashScreenState.Init)

    val scale = remember { Animatable(initialValue = 0f) }
    LaunchedEffect(Unit) {
        viewModel.verifyToken()

        scale.animateTo(
            targetValue = 2.2f,
            animationSpec = tween(
                durationMillis = 1000,
                easing = { curve ->
                    OvershootInterpolator(1f).getInterpolation(curve)
                }
            )
        )
    }

    LaunchedEffect(state) {
        when (state) {
            is SplashScreenState.Success -> {
                navController.navigate(Route.MapScreen) {
                    popUpTo(Route.AuthorizationScreen) { inclusive = true }
                }
            }
            is SplashScreenState.Failure -> {
                navController.navigate(Route.AuthorizationScreen) {
                    popUpTo(Route.AuthorizationScreen) { inclusive = true }
                }
            }
            else -> {
                /* Do nothing */
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = backgroundLight),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.outline_map),
            contentDescription = null
        )
    }
}
