plugins {
    alias(libs.plugins.supportorganizationsapp.android.library)
    alias(libs.plugins.supportorganizationsapp.android.koin)
}

android {
    namespace = "com.mariqzw.supportorganizationsapp.data"
}

dependencies {

    implementation(libs.androidx.datastore.preferences)
}
