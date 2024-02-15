pluginManagement {
    repositories {
        maven("https://maven.fabricmc.net/")
        mavenCentral()
        gradlePluginPortal()
    }

    plugins {
        val loom_version: String by settings
        val kotlin_version: String by settings
        id("fabric-loom") version loom_version
        id("org.jetbrains.kotlin.jvm") version kotlin_version
    }
}
