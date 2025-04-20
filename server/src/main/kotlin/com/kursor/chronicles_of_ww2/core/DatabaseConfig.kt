package com.kursor.chronicles_of_ww2.core

data class DatabaseConfig(
    val url: String,
    val user: String,
    val driver: String,
    val password: String,
) {
    companion object {
        val Debug = DatabaseConfig(
            url = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1",
            user = "root",
            driver = "org.h2.Driver",
            password = "",
        )
    }
}
