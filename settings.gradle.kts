pluginManagement {
    includeBuild("build-source")
    repositories {
        google()
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

rootProject.name = "WLShop"
include(":app")
include(":wlshop")
include(":shared:sa-analytics")
include(":shared:sa-network")
include(":shared:sa-data")
