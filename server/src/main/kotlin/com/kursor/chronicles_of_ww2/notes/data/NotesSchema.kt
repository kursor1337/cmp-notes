package com.kursor.chronicles_of_ww2.notes.data

import com.kursor.chronicles_of_ww2.auth.data.UserService
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

@Serializable
class NoteEntity(
    val id: Long = 0,
    val title: String,
    val content: String,
    val creatorLogin: String
)

class NoteService(database: Database) {
    object Notes : LongIdTable() {
        val title = varchar("title", 255)
        val content = text("content")
        val creatorLogin = reference("creatorLogin", UserService.Users.login)
    }

    init {
        transaction(database) {
            SchemaUtils.create(Notes)
        }
    }

    suspend fun create(note: NoteEntity): Long = dbQuery {
        Notes.insert {
            it[title] = note.title
            it[content] = note.content
            it[creatorLogin] = note.creatorLogin
        }[Notes.id].value
    }

    suspend fun update(note: NoteEntity) = dbQuery {
        Notes.update(
            where = {
                (Notes.id eq note.id) and (Notes.creatorLogin eq note.creatorLogin)
            }
        ) {
            it[title] = note.title
            it[content] = note.content
        }
    }

    suspend fun readAll(login: String): List<NoteEntity> {
        return dbQuery {
            Notes.selectAll()
                .where { Notes.creatorLogin eq login }
                .map {
                    NoteEntity(
                        id = it[Notes.id].value,
                        title = it[Notes.title],
                        content = it[Notes.content],
                        creatorLogin = it[Notes.creatorLogin]
                    )
                }
        }
    }

    private suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }
}
