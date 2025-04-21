package com.kursor.chronicles_of_ww2.features.notes.data

import com.kursor.chronicles_of_ww2.dto.notes.CreateNoteRequest
import com.kursor.chronicles_of_ww2.dto.notes.EditNoteRequest
import com.kursor.chronicles_of_ww2.dto.notes.NoteResponse
import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.POST

interface NoteApi {

    @POST("notes/create")
    suspend fun createNote(
        @Body request: CreateNoteRequest
    )

    @POST("notes/edit")
    suspend fun editNote(
        @Body request: EditNoteRequest
    )

    @GET("notes")
    suspend fun getAllNotes(): List<NoteResponse>
}