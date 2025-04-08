plugins {
    alias(libs.plugins.supportorganizationsapp.android.library)
    alias(libs.plugins.jetbrains.kotlin.serialization)
}

android {
    namespace = "com.mariqzw.supportorganizationsapp.model"

}

dependencies{
    implementation(libs.kotlinx.serialization.json)
}
