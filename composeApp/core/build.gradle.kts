import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.extensions.DetektReportType
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.detekt)
}

kotlin {

    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_21)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(projects.shared)
            implementation(projects.kmpFormValidation)
            implementation(projects.stringDesc)

            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime.compose)
            implementation(libs.kermit.logger)
            implementation(libs.decompose.core)
            implementation(libs.decompose.compose)
            implementation(libs.bundles.replica.common)
            implementation(libs.bundles.ktor.client.common)
            implementation(libs.koin.core)
            implementation(libs.ktorfit.lib)
            implementation(libs.settings)
            implementation(libs.settings.coroutines)
        }

        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.ktor.client.engine.okhttp)
            implementation(libs.replica.android.network)
            implementation(libs.androidx.security)
            implementation(libs.androidx.security.ktx)
        }

        iosMain.dependencies {
            implementation(libs.ktor.client.engine.darwin)
        }
    }
}

android {
    namespace = "com.kursor.chronicles_of_ww2.core"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
}

dependencies {
    coreLibraryDesugaring(libs.android.desugar)
    debugImplementation(compose.uiTooling)
}

// Linters

detekt {
    source.from(files("src"))
    config.from(rootProject.files("code_quality/detekt/config.yml"))
    ignoreFailures = false
    disableDefaultRuleSets = true
}

dependencies {
    detektPlugins(libs.detekt.formatting)
}

tasks.withType<Detekt>().configureEach {
    reports {
        xml {
            outputLocation.set(file("build/reports/detekt-results.xml"))
        }
        html {
            outputLocation.set(file("build/reports/detekt-results.html"))
        }
        txt.required.set(false)
    }
}

android {
    lint {
        abortOnError = true
        warningsAsErrors = false
        checkDependencies = false
        lintConfig = rootProject.file("code_quality/lint/lint-config.xml")
    }
}
