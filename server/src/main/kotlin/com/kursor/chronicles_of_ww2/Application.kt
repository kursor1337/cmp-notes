package com.kursor.chronicles_of_ww2

import com.kursor.chronicles_of_ww2.core.JwtSecret
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
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    val jwtSecret = System.getenv("JWT_SECRET").let(::JwtSecret)
    configureDI(jwtSecret)
    configureLogging()
    configureSecurity(jwtSecret)
    configureRouting()
    configureErrorHandling()
    configureSerialization()
}