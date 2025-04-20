package com.kursor.chronicles_of_ww2.plugins

import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.statuspages.StatusPages

fun Application.configureErrorHandling() {
    install(StatusPages) {
        exception<Exception> { call, e ->
            e.printStackTrace()
        }
    }
}
