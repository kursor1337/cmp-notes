package com.kursor.chronicles_of_ww2.plugins

import com.kursor.chronicles_of_ww2.auth.authModule
import com.kursor.chronicles_of_ww2.core.DatabaseConfig
import com.kursor.chronicles_of_ww2.core.JwtSecret
import com.kursor.chronicles_of_ww2.core.coreModule
import com.kursor.chronicles_of_ww2.notes.notesModule
import io.ktor.server.application.Application
import io.ktor.server.application.install
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.configureDI(jwtSecret: JwtSecret) {
    install(Koin) {
        slf4jLogger()
        modules(
            coreModule(
                jwtSecret = jwtSecret,
                databaseConfig = DatabaseConfig.Debug
            ),
            authModule,
            notesModule
        )
    }
}
