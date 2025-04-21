import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.extensions.DetektReportType
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.detekt)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
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
            export(libs.decompose.core)
            export(libs.essenty.lifecycle)
        }
    }
    
    sourceSets {
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.splashscreen)
            implementation(libs.bundles.hyperion)
            implementation(libs.chucker)
            implementation(libs.ktor.client.engine.okhttp)
            implementation(libs.replica.devtools)
        }
        commonMain.dependencies {
            implementation(projects.shared)
            implementation(projects.composeApp.core)
            implementation(projects.composeApp.features)

            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime.compose)
            implementation(libs.koin.core)
            implementation(libs.bundles.replica.common)
            implementation(libs.kermit.logger)
            api(libs.decompose.core)
            implementation(libs.decompose.compose)
        }
        iosMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
        }
    }
}

android {
    namespace = "com.kursor.chronicles_of_ww2"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.kursor.chronicles_of_ww2"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures {
        buildConfig = true
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = true
            isShrinkResources = true
            setProguardFiles(
                listOf(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            )
        }
    }

    setFlavorDimensions(listOf("backend"))
    productFlavors {
        create("dev") {
            dimension = "backend"
        }

        create("prod") {
            dimension = "backend"
        }
    }

    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    packaging {
        resources {
            excludes += listOf(
                "META-INF/INDEX.LIST",
                "META-INF/io.netty.versions.properties"
            )
        }
    }
}

dependencies {
    coreLibraryDesugaring(libs.android.desugar)
    debugImplementation(compose.uiTooling)
}

compose.desktop {
    application {
        mainClass = "com.kursor.chronicles_of_ww2.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.kursor.chronicles_of_ww2"
            packageVersion = "1.0.0"
        }
    }
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
