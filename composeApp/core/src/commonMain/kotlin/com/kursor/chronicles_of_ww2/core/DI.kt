package com.kursor.chronicles_of_ww2.core

import com.kursor.chronicles_of_ww2.core.configuration.BuildType
import com.kursor.chronicles_of_ww2.core.configuration.Configuration
import com.kursor.chronicles_of_ww2.core.error_handling.ErrorHandler
import com.kursor.chronicles_of_ww2.core.message.data.MessageService
import com.kursor.chronicles_of_ww2.core.message.data.MessageServiceImpl
import com.kursor.chronicles_of_ww2.core.network.BackendUrl
import com.kursor.chronicles_of_ww2.core.network.NetworkApiFactory
import me.aartikov.replica.client.ReplicaClient
import org.koin.core.module.Module
import org.koin.dsl.module

fun commonCoreModule(configuration: Configuration) = module {
    single { configuration }
    single { ReplicaClient(getOrNull()) }
    single<MessageService> { MessageServiceImpl() }
    single { ErrorHandler(get()) }
    single {
        NetworkApiFactory(
            loggingEnabled = configuration.buildType == BuildType.Debug,
            backendUrl = BackendUrl.getMainUrl(configuration.backend),
            httpClientEngine = get(),
            tokenRefresher = inject(),
            tokenProvider = get()
        )
    }
}

expect fun platformCoreModule(configuration: Configuration): Module