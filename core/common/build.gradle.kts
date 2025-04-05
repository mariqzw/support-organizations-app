plugins {
    alias(libs.plugins.supportorganizationsapp.android.library)
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
}
