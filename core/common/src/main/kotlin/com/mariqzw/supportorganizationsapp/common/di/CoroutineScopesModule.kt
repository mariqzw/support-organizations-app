package com.mariqzw.supportorganizationsapp.common.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.core.qualifier.named
import org.koin.dsl.module

val provideCoroutineScopesModule = module {
    single {
        val coroutineDispatcher = get<CoroutineDispatcher>(named(SaDispatchers.DEFAULT.name))
        CoroutineScope(SupervisorJob() + coroutineDispatcher)
    }
}
