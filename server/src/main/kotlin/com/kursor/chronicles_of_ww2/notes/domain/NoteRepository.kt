package com.kursor.chronicles_of_ww2.notes.domain

import com.kursor.chronicles_of_ww2.auth.domain.Login

interface NoteRepository {

    suspend fun createNote(
        userLogin: Login,
        title: String,
        content: String
    ): NoteId

    suspend fun editNote(
        userLogin: Login,
        noteId: NoteId,
        title: String,
        content: String
    )

    suspend fun getAllNotesForUser(
        userLogin: Login
    ): List<Note>
}