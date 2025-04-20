package com.kursor.chronicles_of_ww2.core

import io.ktor.client.engine.darwin.Darwin
import org.koin.dsl.module
import ru.mobileup.kmm_template.core.configuration.Configuration

actual fun platformCoreModule(configuration: Configuration) = module {
    single { Darwin.create() }
}