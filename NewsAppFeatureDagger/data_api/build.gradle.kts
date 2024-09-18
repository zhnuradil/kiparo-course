/*
 * Copyright (c) 2023. Kiparo.ru
 */

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("java-library")
    alias(libs.plugins.org.jetbrains.kotlin.jvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(project(":domain_models"))
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.javax.inject)

}