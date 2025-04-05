plugins {
    alias(libs.plugins.supportorganizationsapp.android.library)
    alias(libs.plugins.supportorganizationsapp.android.library.compose)
    alias(libs.plugins.supportorganizationsapp.android.koin)
}

android {
    namespace = "com.mariqzw.supportorganizationsapp.chats"

}

dependencies {

    /**
     * Core dependencies
     */
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.kotlinx.datetime)

    /**
     *  Module dependencies
     */
    implementation(project(":core:ui"))
    implementation(project(":core:data"))
    implementation(project(":core:domain"))
    implementation(project(":core:model"))
    implementation(project(":core:common"))
}
