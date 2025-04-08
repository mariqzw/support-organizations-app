plugins {
    alias(libs.plugins.supportorganizationsapp.android.library)
    alias(libs.plugins.supportorganizationsapp.android.ktor)
    alias(libs.plugins.supportorganizationsapp.android.koin)
}

android {
    namespace = "com.mariqzw.supportorganizationsapp.network"

    defaultConfig {
        buildConfigField("String", "WEB_HOST", "\"192.168.0.203\"") // TODO: Изменить IP
    }

    buildFeatures {
        buildConfig = true
    }

    testOptions {
        unitTests.isReturnDefaultValues = true
    }
}

dependencies {

    /**
     * Module dependencies
     */
    implementation(project(":core:common"))
    implementation(project(":core:data"))
    implementation(project(":core:domain"))
    implementation(project(":core:model"))
    testImplementation(project(":core:testing"))
}
