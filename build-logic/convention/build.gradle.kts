import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

group = "com.mariqzw.supportorganizationsapp.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_17
    }
}

dependencies {
    compileOnly(libs.gradle.plugin.android)
    compileOnly(libs.gradle.plugin.kotlin)
    compileOnly(libs.gradle.plugin.ksp)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "supportorganizationsapp.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }

        register("androidApplicationCompose") {
            id = "supportorganizationsapp.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }

        register("androidLibrary") {
            id = "supportorganizationsapp.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }

        register("androidLibraryCompose") {
            id = "supportorganizationsapp.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }

        register("androidKtor") {
            id = "supportorganizationsapp.android.ktor"
            implementationClass = "AndroidKtorConventionPlugin"
        }

        register("androidKoin") {
            id = "supportorganizationsapp.android.koin"
            implementationClass = "AndroidKoinConventionPlugin"
        }

        register("androidRoom") {
            id = "supportorganizationsapp.android.room"
            implementationClass = "AndroidRoomConventionPlugin"
        }
    }
}
