pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        val githubProperties = java.util.Properties()
        githubProperties.load(java.io.FileInputStream(file("github.properties")))
        maven {
            name = "GithubPackages"
            url = uri("https://maven.pkg.github.com/mobileappconsultant/ulessonsharedlibrary")
            credentials {
                username = githubProperties["gpr.user"] as String? ?: System.getenv("GPR_USER")
                password = githubProperties["gpr.key"] as String? ?: System.getenv("GPR_KEY")
            }
        }
    }
}

rootProject.name = "ULessonTechnicalTest"
include(":uLessonAndroid")
