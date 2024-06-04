import java.net.URL

pluginManagement {
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
//        maven("https://maven.aliyun.com/repository/google")
        maven("https://jcenter.bintray.com/")
//        maven{ uri("https://maven.aliyun.com/repository/google")}
    }
}

rootProject.name = "fridge_partner"
include(":app")
 