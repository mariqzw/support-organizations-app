package com.mariqzw.supportorganizationsapp.common.di

import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.module

val provideDispatcherModule = module {
    single(named(SaDispatchers.DEFAULT.name)) {
        Dispatchers.Default
    }

    single(named(SaDispatchers.IO.name)) {
        Dispatchers.IO
    }
}

enum class SaDispatchers {
    IO,
    DEFAULT
}