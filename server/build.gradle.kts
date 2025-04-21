import io.gitlab.arturbosch.detekt.Detekt
import io.ktor.plugin.features.DockerImageRegistry
import io.ktor.plugin.features.DockerPortMapping
import io.ktor.plugin.features.DockerPortMappingProtocol
import java.util.Properties

plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ktor)
    alias(libs.plugins.detekt)
    alias(libs.plugins.kotlin.plugin.serialization)
    application
}

val localProperties = Properties().apply {
    load(project.file("local.properties").inputStream())
}

group = "com.kursor.chronicles_of_ww2"
version = "1.0.0"
application {
    mainClass.set("com.kursor.chronicles_of_ww2.ApplicationKt")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=${extra["io.ktor.development"] ?: "false"}")
}

dependencies {
    implementation(projects.shared)
    implementation(libs.bundles.ktor.server)
    implementation(libs.bcrypt)
    testImplementation(libs.kotlin.test.junit)
}

ktor {
    docker {
        localImageName.set("kursor1337/cmp-notes")
        imageTag.set("latest")
        portMappings.set(
            listOf(
                DockerPortMapping(
                    8080,
                    8080,
                    DockerPortMappingProtocol.TCP
                )
            )
        )

        localProperties.forEach {
            environmentVariable(
                it.key.toString(),
                it.value.toString()
            )
        }

        externalRegistry.set(
            DockerImageRegistry.dockerHub(
                appName = provider { "cmp-notes" },
                username = provider { localProperties.getProperty("DOCKER_HUB_USERNAME") },
                password = provider { localProperties.getProperty("DOCKER_HUB_PASSWORD") }
            )
        )

        jib {
            from {
                image = "openjdk:17-jdk-alpine"
            }
            to {
                image = "${localProperties.getProperty("DOCKER_HUB_USERNAME")}/cmp-notes"
                tags = setOf("${project.version}")
            }
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
