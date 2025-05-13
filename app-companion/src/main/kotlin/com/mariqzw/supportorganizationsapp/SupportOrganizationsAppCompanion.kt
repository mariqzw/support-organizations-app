package com.mariqzw.supportorganizationsapp

import android.app.Application
import com.mariqzw.supportorganizationsapp.di.provideNavigationModule
import com.mariqzw.supportorganizationsapp.di.provideNetworkEndpointsModule
import com.mariqzw.supportorganizationsapp.di.provideNetworkModule
import com.mariqzw.supportorganizationsapp.di.provideViewModelModule
import com.mariqzw.supportorganizationsapp.reporting.ConsoleLoggingTree
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class SupportOrganizationsAppCompanion : Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(ConsoleLoggingTree())

        startKoin {
            androidLogger()
            androidContext(this@SupportOrganizationsAppCompanion)

            // :app modules
            modules(provideNavigationModule, provideViewModelModule)

            // core:network modules
            modules(provideNetworkModule, provideNetworkEndpointsModule)
        }
    }
}
