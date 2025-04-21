import io.gitlab.arturbosch.detekt.Detekt
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.module.graph)
    alias(libs.plugins.detekt)
    alias(libs.plugins.kotlin.serialization)
}


kotlin {
    
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_21)
        }
    }
    
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    jvm()
    
    sourceSets {
        commonMain.dependencies {
            // put your Multiplatform dependencies here
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.serialization.json)
        }
    }
}

// Android target setup

android {
    namespace = "com.kursor.chronicles_of_ww2.shared"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}

// Linters

moduleGraph {
    featuresPackage.set("com.kursor.chronicles_of_ww2.engine")
    featuresDirectory.set(project.file("src/commonMain/kotlin/com/kursor/chronicles_of_ww2/engine"))
    outputDirectory.set(project.file("module_graph"))
}

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
