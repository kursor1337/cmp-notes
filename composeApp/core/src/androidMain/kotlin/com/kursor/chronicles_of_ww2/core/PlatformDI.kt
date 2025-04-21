package com.kursor.chronicles_of_ww2.core

import android.app.Application
import android.content.Context
import com.kursor.chronicles_of_ww2.core.activity.ActivityProvider
import com.kursor.chronicles_of_ww2.core.configuration.Configuration
import com.kursor.chronicles_of_ww2.core.network.createOkHttpEngine
import com.kursor.chronicles_of_ww2.core.settings.AndroidSettingsFactory
import com.kursor.chronicles_of_ww2.core.settings.SettingsFactory
import me.aartikov.replica.network.AndroidNetworkConnectivityProvider
import me.aartikov.replica.network.NetworkConnectivityProvider
import org.koin.dsl.module

actual fun platformCoreModule(configuration: Configuration) = module {
    single { get<Configuration>().platform.application }
    single { get<Configuration>().platform.debugTools }
    single<Context> { get<Application>() }
    single { ActivityProvider() }
    single { createOkHttpEngine(get()) }
    single<NetworkConnectivityProvider> { AndroidNetworkConnectivityProvider(get()) }
    single<SettingsFactory> { AndroidSettingsFactory(get()) }
}