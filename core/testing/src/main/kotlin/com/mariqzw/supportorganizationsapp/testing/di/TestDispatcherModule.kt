package com.mariqzw.supportorganizationsapp.testing.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.koin.core.qualifier.named
import org.koin.dsl.module

@OptIn(ExperimentalCoroutinesApi::class)
val provideTestDispatcherModule = module {
    single<TestDispatcher> {
        UnconfinedTestDispatcher()
    }

    single<CoroutineDispatcher>(named(SoDispatchers.DEFAULT.name)) {
        get<TestDispatcher>()
    }

    single<CoroutineDispatcher>(named(SoDispatchers.IO.name)) {
        get<TestDispatcher>()
    }
}

enum class SoDispatchers {
    IO,
    DEFAULT
}
