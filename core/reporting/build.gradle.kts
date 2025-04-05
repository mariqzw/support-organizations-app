plugins {
    alias(libs.plugins.supportorganizationsapp.android.library)
}

android {
    namespace = "com.mariqzw.supportorganizationsapp.reporting"

}

dependencies {

    /**
     * Module dependencies
     */
    implementation(project(":core:domain"))
}
