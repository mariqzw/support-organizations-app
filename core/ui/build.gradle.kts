plugins {
    alias(libs.plugins.supportorganizationsapp.android.library.compose)
}

android {
    namespace = "com.mariqzw.supportorganizationsapp.ui"

}

dependencies {

    /**
     * Core dependencies
     */
    implementation(libs.androidx.core.ktx)
    implementation(libs.kotlinx.datetime)

    /**
     * Navigation dependencies
     */
    implementation(libs.androidx.navigation.compose)

    /**
     *  Module dependencies
     */
    implementation(project(":core:domain"))
    implementation(project(":core:model"))
}
