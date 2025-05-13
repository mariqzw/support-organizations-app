package com.mariqzw.supportorganizationsapp.di

import com.mariqzw.supportorganizationsapp.ui.R
import com.mariqzw.supportorganizationsapp.ui.components.navigation.BottomNavItem
import org.koin.dsl.module

val provideNavigationModule = module {
    single {
        listOf(
            BottomNavItem(
                R.drawable.outline_map
            ),
            BottomNavItem(
                R.drawable.outline_list
            ),
            BottomNavItem(
                R.drawable.chat_bubble
            ),
            BottomNavItem(
                R.drawable.account_circle
            )
        )
    }
}
