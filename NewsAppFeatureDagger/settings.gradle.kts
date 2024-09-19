pluginManagement {
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

rootProject.name = "KiparoNewsAppFeatureDagger"
include(":app")
include(":core:navigation")
include(":core:ui")
include(":data")
include(":data_api")
include(":domain_models")
include(":features:article_details")
include(":features:article_details_api")
include(":features:news")
include(":features:news_api")