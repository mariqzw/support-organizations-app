package com.mariqzw.supportorganizationsapp

import android.app.Application
import com.mariqzw.supportorganizationsapp.di.provideNetworkEndpointsModule
import com.mariqzw.supportorganizationsapp.di.provideNetworkModule
import com.mariqzw.supportorganizationsapp.reporting.ConsoleLoggingTree
import org.koin.core.context.startKoin
import timber.log.Timber

class SupportOrganizationsApp : Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(ConsoleLoggingTree())

        startKoin {

            // core:network modules
            modules(provideNetworkModule, provideNetworkEndpointsModule)
        }
    }
}
