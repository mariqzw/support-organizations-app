plugins {
    alias(libs.plugins.supportorganizationsapp.android.library)
    alias(libs.plugins.supportorganizationsapp.android.koin)
}

android {
    namespace = "com.mariqzw.supportorganizationsapp.data"
}

dependencies {

    /**
     * Module dependencies
     */
    implementation(project(":core:domain"))
    implementation(project(":core:model"))

    implementation(libs.androidx.datastore.preferences)
}
