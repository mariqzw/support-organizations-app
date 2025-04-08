plugins {
    alias(libs.plugins.supportorganizationsapp.android.library)
    alias(libs.plugins.supportorganizationsapp.android.room)
}

android {
    namespace = "com.mariqzw.supportorganizationsapp.database"

}

dependencies {

    /**
     * Module dependencies
     */
    implementation(project(":core:common"))
    implementation(project(":core:domain"))
    implementation(project(":core:model"))
}
