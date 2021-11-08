/*
pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    plugins {
        id 'com.android.application' version '7.1.0-alpha13'
        id 'com.android.library' version '7.1.0-alpha13'
        id 'org.jetbrains.kotlin.android' version '1.5.21'
    }
}
dependencyResolutionManagement {*/

rootProject.name = "GitApp"
include(":app")
include(":repos")
include(":reposui")
include(":navigator")
