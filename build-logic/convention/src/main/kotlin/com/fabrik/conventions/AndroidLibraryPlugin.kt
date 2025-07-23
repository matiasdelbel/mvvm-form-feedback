package com.fabrik.conventions

import com.android.build.gradle.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

class AndroidLibraryPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        project.plugins.apply("com.android.library")
        project.plugins.apply("org.jetbrains.kotlin.android")

        project.extensions.configure<LibraryExtension> {
            compileSdk = 35

            buildTypes {
                release { isMinifyEnabled = false }
            }

            defaultConfig {
                minSdk = 26

                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                consumerProguardFiles("consumer-rules.pro")
            }

            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_17
                targetCompatibility = JavaVersion.VERSION_17
            }

            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }

    private fun LibraryExtension.kotlinOptions(configure: KotlinJvmOptions.() -> Unit) {
        (this as ExtensionAware)
            .extensions.configure("kotlinOptions", configure)
    }
}