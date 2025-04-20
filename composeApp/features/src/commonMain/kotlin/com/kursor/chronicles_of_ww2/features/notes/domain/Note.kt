package com.kursor.chronicles_of_ww2.features.notes.domain

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@Serializable
@JvmInline
value class NoteId(val value: Long)

@Serializable
data class Note(
    val id: NoteId,
    val title: String,
    val content: String
) {
    companion object {
        val MOCK = Note(
            title = "title",
            content = "content",
            id = NoteId(1)
        )
    }
}
