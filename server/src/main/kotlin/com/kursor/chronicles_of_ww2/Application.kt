package com.kursor.chronicles_of_ww2

import com.kursor.chronicles_of_ww2.plugins.configureDI
import com.kursor.chronicles_of_ww2.plugins.configureErrorHandling
import com.kursor.chronicles_of_ww2.plugins.configureLogging
import com.kursor.chronicles_of_ww2.plugins.configureRouting
import com.kursor.chronicles_of_ww2.plugins.configureSecurity
import com.kursor.chronicles_of_ww2.plugins.configureSerialization
import io.ktor.server.application.Application
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun main() {
    embeddedServer(Netty, port = SERVER_PORT, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureDI()
    configureLogging()
    configureSecurity()
    configureRouting()
    configureErrorHandling()
    configureSerialization()
}