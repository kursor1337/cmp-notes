package com.kursor.chronicles_of_ww2.notes.domain

import com.kursor.chronicles_of_ww2.auth.domain.Login
import kotlinx.serialization.Serializable

@Serializable
@JvmInline
value class NoteId(val value: Long)

@Serializable
data class Note(
    val id: NoteId,
    val title: String,
    val content: String,
    val creatorLogin: Login
)
