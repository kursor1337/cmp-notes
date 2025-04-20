package com.kursor.chronicles_of_ww2.core

import org.jetbrains.exposed.sql.Database
import org.koin.dsl.module

fun coreModule(
    jwtSecret: JwtSecret,
    databaseConfig: DatabaseConfig
) = module {
    single<JwtSecret> { jwtSecret }
    single<Database> {
        Database.connect(
            url = databaseConfig.url,
            driver = databaseConfig.driver,
            user = databaseConfig.user,
            password = databaseConfig.password
        )
    }
}