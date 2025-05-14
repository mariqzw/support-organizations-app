package com.mariqzw.supportorganizationsapp.auth.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavController
import com.mariqzw.supportorganizationsapp.domain.navigation.Route
import com.mariqzw.supportorganizationsapp.ui.R
import com.mariqzw.supportorganizationsapp.ui.theme.backgroundLight

@Composable
fun SplashScreen(
    navController: NavController
) {
    LaunchedEffect(Unit) {
        navController.navigate(Route.MapScreen) {
            popUpTo(0) { inclusive = true } // удаляет Splash из back stack
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
