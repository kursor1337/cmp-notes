package com.kursor.chronicles_of_ww2.plugins

import com.kursor.chronicles_of_ww2.auth.routing.configureAuthRouting
import com.kursor.chronicles_of_ww2.notes.routing.configureNotesRouting
import io.ktor.server.application.*
import io.ktor.server.routing.route
import io.ktor.server.routing.routing
import org.koin.ktor.ext.get

fun Application.configureRouting() {
    configureAuthRouting(get(), get())
    configureNotesRouting(get())
}
