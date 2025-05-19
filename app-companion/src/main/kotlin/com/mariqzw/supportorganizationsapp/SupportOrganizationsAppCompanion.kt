package com.mariqzw.supportorganizationsapp

import android.app.Application
import com.mariqzw.supportorganizationsapp.applications.di.provideApplicationsModule
import com.mariqzw.supportorganizationsapp.auth.di.provideAuthModule
import com.mariqzw.supportorganizationsapp.common.di.provideCoroutineScopesModule
import com.mariqzw.supportorganizationsapp.common.di.provideDispatcherModule
import com.mariqzw.supportorganizationsapp.data.di.provideDataStoreModule
import com.mariqzw.supportorganizationsapp.di.provideNavigationModule
import com.mariqzw.supportorganizationsapp.di.provideViewModelModule
import com.mariqzw.supportorganizationsapp.network.di.provideNetworkEndpointsModule
import com.mariqzw.supportorganizationsapp.network.di.provideNetworkModule
import com.mariqzw.supportorganizationsapp.profile.di.provideProfileModule
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

            // core:common modules
            modules(provideCoroutineScopesModule, provideDispatcherModule)

            // core:data modules
            modules(provideDataStoreModule)

            // core:network modules
            modules(provideNetworkModule, provideNetworkEndpointsModule)

            // feature:ui-auth modules
            modules(provideAuthModule)

            // feature:ui-applications modules
            modules(provideApplicationsModule)

            // feature:ui-profile modules
            modules(provideProfileModule)
        }
    }
}
