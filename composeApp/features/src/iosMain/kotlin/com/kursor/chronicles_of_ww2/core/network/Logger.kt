package com.kursor.chronicles_of_ww2.core.network

import io.ktor.client.plugins.logging.Logger
import co.touchlab.kermit.Logger as KermitLogger

actual fun createKtorLogger(): Logger {
    val kermitLogger = KermitLogger.withTag("HttpClient")
    return object : Logger {
        override fun log(message: String) {
            kermitLogger.i(message)
        }
    }
}