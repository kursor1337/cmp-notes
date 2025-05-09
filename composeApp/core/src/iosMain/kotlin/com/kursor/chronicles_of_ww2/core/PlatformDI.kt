package com.kursor.chronicles_of_ww2.core

import com.kursor.chronicles_of_ww2.core.configuration.Configuration
import com.kursor.chronicles_of_ww2.core.settings.IosSettingsFactory
import com.kursor.chronicles_of_ww2.core.settings.SettingsFactory
import io.ktor.client.engine.darwin.Darwin
import org.koin.core.module.single
import org.koin.dsl.module

actual fun platformCoreModule(configuration: Configuration) = module {
    single { Darwin.create() }
    single<SettingsFactory> { IosSettingsFactory() }
}