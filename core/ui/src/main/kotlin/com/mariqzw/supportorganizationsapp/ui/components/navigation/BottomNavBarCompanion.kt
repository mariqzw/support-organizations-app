package com.mariqzw.supportorganizationsapp.ui.components.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.mariqzw.supportorganizationsapp.ui.theme.LocalDimensions
import com.mariqzw.supportorganizationsapp.ui.theme.onBackgroundLight
import com.mariqzw.supportorganizationsapp.ui.theme.onSurfaceVariantLight
import com.mariqzw.supportorganizationsapp.ui.theme.secondaryContainerLight
import com.mariqzw.supportorganizationsapp.ui.theme.surfaceContainerLight
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun BottomNavBarCompanion(
    navController: NavController,
    navItems: List<BottomNavItem>,
    scope: CoroutineScope,
    modifier: Modifier = Modifier
) {
    val dimensions = LocalDimensions.current

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(
        modifier = modifier
            .height(dimensions.bottomNavBarHeight),
        containerColor = surfaceContainerLight,
        contentColor = onSurfaceVariantLight
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = dimensions.verticalXXSmall),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            navItems.forEachIndexed { index, item ->
                val route = when (index) {
                    0 -> "com.mariqzw.supportorganizationsapp.domain.navigation.Route.MapScreen"
                    1 -> "com.mariqzw.supportorganizationsapp.domain.navigation.Route.ApplicationsCompanionListScreen"
                    2 -> "com.mariqzw.supportorganizationsapp.domain.navigation.Route.ChatsListScreen"
                    3 -> "com.mariqzw.supportorganizationsapp.domain.navigation.Route.ProfileScreen"
                    else -> ""
                }

                val isSelected = (currentRoute == route)

                NavigationBarItem(
                    selected = isSelected,
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = Color.Transparent, // убираем стандартную подсветку
                        selectedIconColor = onSurfaceVariantLight,
                        unselectedIconColor = onSurfaceVariantLight
                    ),
                    icon = {
                        Box(
                            modifier = if (isSelected) {
                                Modifier
                                    .fillMaxWidth()
                                    .clip(CircleShape)
                                    .background(color = secondaryContainerLight)
                                    .padding(vertical = dimensions.verticalXXSmall)// внутренний отступ до иконки
                            } else {
                                Modifier.size(dimensions.iconSize) // просто иконка без фона
                            },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(item.icon),
                                contentDescription = null,
                                tint = onBackgroundLight
                            )
                        }

                    },
                    label = null, // Текста нет
                    alwaysShowLabel = false, // Чтобы подписи не появлялись
                    onClick = {
                        scope.launch {
                            if (!isSelected) {
                                navController.navigate(route) {
                                    launchSingleTop = true
                                    restoreState = true
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                }
                            }
                        }
                    }
                )
            }
        }
    }
}
