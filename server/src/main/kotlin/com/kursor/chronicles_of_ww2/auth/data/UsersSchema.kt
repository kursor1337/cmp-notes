package com.kursor.chronicles_of_ww2.auth.data

import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

@Serializable
class UserEntity(
    val login: String,
    val passwordHash: String
)

class UserService(database: Database) {
    object Users : Table() {
        val login = varchar("login", 50)
        val passwordHash = text("password_hash")

        override val primaryKey = PrimaryKey(login)
    }

    init {
        transaction(database) {
            SchemaUtils.create(Users)
        }
    }

    suspend fun create(user: UserEntity): String = dbQuery {
        Users.insert {
            it[login] = user.login
            it[passwordHash] = user.passwordHash
        }[Users.login]
    }

    suspend fun read(login: String): UserEntity? {
        return dbQuery {
            Users.selectAll()
                .where { Users.login eq login }
                .map { UserEntity(it[Users.login], it[Users.passwordHash]) }
                .singleOrNull()
        }
    }

    private suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }
}
