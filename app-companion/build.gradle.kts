plugins {
    alias(libs.plugins.supportorganizationsapp.android.application)
    alias(libs.plugins.supportorganizationsapp.android.application.compose)
    alias(libs.plugins.supportorganizationsapp.android.koin)
}

android {
    namespace = "com.mariqzw.supportorganizationsapp"
    compileSdk = libs.versions.compile.sdk.get().toInt()

    defaultConfig {
        applicationId = "com.mariqzw.supportorganizationsapp"
        versionCode = libs.versions.version.code.get().toInt()
        versionName = libs.versions.version.name.get()
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {

    /**
     * Core dependencies
     */
    implementation(libs.androidx.core.ktx)

    /**
     * Navigation dependencies
     */
    implementation(libs.androidx.navigation.compose)

    /**
     *  Module dependencies
     */
    implementation(project(":core:common"))
    implementation(project(":core:data"))
    implementation(project(":core:database"))
    implementation(project(":core:domain"))
    implementation(project(":core:model"))
    implementation(project(":core:network"))
    implementation(project(":core:reporting"))
    implementation(project(":core:ui"))
    implementation(project(":feature:ui-auth"))
    implementation(project(":feature:ui-applications"))
    implementation(project(":feature:ui-add-applications"))
    implementation(project(":feature:ui-chats"))
    implementation(project(":feature:ui-map-metro"))
    implementation(project(":feature:ui-profile"))
}
