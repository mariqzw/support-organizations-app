plugins {
    alias(libs.plugins.supportorganizationsapp.android.library)
    alias(libs.plugins.jetbrains.kotlin.serialization)
}

android {
    namespace = "com.mariqzw.supportorganizationsapp.domain"

}

dependencies {

    /**
     * Module dependencies
     */
    implementation(project(":core:model"))

    /**
     * Coroutines dependencies
     */
    implementation(libs.kotlinx.coroutines.android)

    implementation(libs.kotlinx.serialization.json)
}
