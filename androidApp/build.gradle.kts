import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("plugin.serialization") version("1.5.20")
    id("org.jlleitschuh.gradle.ktlint") version ("11.0.0")
}

android {
    namespace = "ru.fursa.unsplash.android"
    compileSdk = 33
    defaultConfig {
        applicationId = "ru.fursa.unsplash.android"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.0"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

tasks.getByPath("preBuild").dependsOn("ktlintFormat")

ktlint {
    android.set(true)
    reporters {
        reporter(reporterType = ReporterType.PLAIN)
        reporter(reporterType = ReporterType.CHECKSTYLE)
        reporter(reporterType = ReporterType.SARIF)
    }
}

dependencies {
    implementation(project(":shared"))
    // implementation(project(":ui-kit"))

    implementation("androidx.compose.ui:ui:1.4.3")
    implementation("androidx.compose.ui:ui-tooling:1.4.3")
    implementation("androidx.compose.ui:ui-tooling-preview:1.4.3")
    implementation("androidx.compose.foundation:foundation:1.4.3")
    implementation("androidx.compose.material:material:1.4.3")
    implementation("androidx.activity:activity-compose:1.7.2")

    val koinVersion = "3.2.0"
    val coilVersion = "2.4.0"
    // Koin
    implementation("io.insert-koin:koin-core:$koinVersion")
    implementation("io.insert-koin:koin-test:$koinVersion")
    implementation("io.insert-koin:koin-android:$koinVersion")
    implementation("io.insert-koin:koin-androidx-compose:$koinVersion")

    implementation("io.coil-kt:coil-compose:$coilVersion")

    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")
    implementation("androidx.navigation:navigation-compose:2.6.0")
    implementation("com.google.accompanist:accompanist-navigation-animation:0.16.0")

    implementation("androidx.paging:paging-compose:3.2.0")
    implementation("androidx.paging:paging-runtime:3.2.0")
}
