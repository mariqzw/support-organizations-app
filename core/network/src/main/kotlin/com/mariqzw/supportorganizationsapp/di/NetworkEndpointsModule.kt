package com.mariqzw.supportorganizationsapp.di

import com.mariqzw.supportorganizationsapp.network.BuildConfig
import org.koin.core.qualifier.named
import org.koin.dsl.module

val provideNetworkEndpointsModule = module {
    single(named("API")) {
        BuildConfig.WEB_HOST
    }
}
