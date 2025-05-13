pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "SupportOrganizationsApp"
include(":app")
include(":app-companion")
include(":core:common")
include(":core:data")
include(":core:database")
include(":core:domain")
include(":core:model")
include(":core:network")
include(":core:reporting")
include(":core:testing")
include(":core:ui")
include(":feature:ui-auth")
include(":feature:ui-map-metro")
include(":feature:ui-applications")
include(":feature:ui-add-applications")
include(":feature:ui-profile")
include(":feature:ui-chats")
