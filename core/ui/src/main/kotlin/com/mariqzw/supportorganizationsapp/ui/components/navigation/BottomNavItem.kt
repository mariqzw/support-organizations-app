package com.mariqzw.supportorganizationsapp.ui.components.navigation

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Stable

/**
 * Представляет собой нижний элемент навигации со значком.
 *
 * @property [icon] Идентификатор ресурса значка, представляющего иконку для элемента навигации.
 */
@Stable
@SuppressLint("SupportAnnotationUsage")
data class BottomNavItem (
    @DrawableRes val icon: Int
)
