package com.kursor.chronicles_of_ww2.notes.data

import com.kursor.chronicles_of_ww2.auth.domain.Login
import com.kursor.chronicles_of_ww2.notes.domain.Note
import com.kursor.chronicles_of_ww2.notes.domain.NoteId
import com.kursor.chronicles_of_ww2.notes.domain.NoteRepository
import org.jetbrains.exposed.sql.not

class NoteRepositoryImpl(private val noteService: NoteService) : NoteRepository {
    override suspend fun createNote(
        userLogin: Login,
        title: String,
        content: String
    ): NoteId {
        return noteService
            .create(
                NoteEntity(
                    title = title,
                    content = content,
                    creatorLogin = userLogin.value
                )
            )
            .let(::NoteId)
    }

    override suspend fun editNote(
        userLogin: Login,
        noteId: NoteId,
        title: String,
        content: String
    ) {
        noteService
            .update(
                NoteEntity(
                    id = noteId.value,
                    title = title,
                    content = content,
                    creatorLogin = userLogin.value
                )
            )
    }

    override suspend fun getAllNotesForUser(userLogin: Login): List<Note> {
        return noteService.readAll(userLogin.value).map { it.toDomain() }
    }
}

fun NoteEntity.toDomain() = Note(
    id = NoteId(id),
    title = title,
    content = content,
    creatorLogin = Login(creatorLogin)
)