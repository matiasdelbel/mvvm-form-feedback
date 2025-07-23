import com.github.takahirom.roborazzi.ExperimentalRoborazziApi

plugins {
    alias(libs.plugins.convention.android.library)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.roborazzi)
}

android {
    namespace = "com.fabrik.design"

    testOptions {
        unitTests {
            isIncludeAndroidResources = true

            all { test -> test.systemProperties["robolectric.pixelCopyRenderMode"] = "hardware" }
        }
    }
}

roborazzi {
    @OptIn(ExperimentalRoborazziApi::class)
    generateComposePreviewRobolectricTests {
        enable = true
        includePrivatePreviews = false
        packages = listOf("com.fabrik.design")
    }

    outputDir.set(file("screenshots"))
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    api(platform(libs.androidx.compose.bom))
    api(libs.androidx.material3)
    api(libs.androidx.ui)
    api(libs.androidx.ui.graphics)
    api(libs.androidx.ui.tooling.preview)
    api(libs.androidx.material3)

    debugImplementation(libs.androidx.ui.tooling)

    testImplementation(libs.junit)
    testImplementation(libs.preview.scanner.compose)
    testImplementation(libs.preview.scanner.roborazzi)
    testImplementation(libs.robolectric)
}