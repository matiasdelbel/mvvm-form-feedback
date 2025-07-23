plugins {
    `kotlin-dsl`
    kotlin("jvm") version libs.versions.kotlin
}

dependencies {
    implementation(libs.android.tools.build.gradle)
    implementation(libs.android.kotlin.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("androidLibraryConvention") {
            id = libs.plugins.convention.android.library.get().pluginId
            implementationClass = "com.fabrik.conventions.AndroidLibraryPlugin"
        }
    }
}
