plugins {
    alias(libs.plugins.supportorganizationsapp.android.library)
    alias(libs.plugins.supportorganizationsapp.android.koin)
}

android {
    namespace = "com.mariqzw.supportorganizationsapp.common"

    testOptions {
        unitTests.isReturnDefaultValues = true
    }
}

dependencies {

    /**
     * Core dependencies
     */
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.android)
}
