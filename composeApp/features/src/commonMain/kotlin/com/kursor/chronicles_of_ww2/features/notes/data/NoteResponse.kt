package com.kursor.chronicles_of_ww2.features.notes.data

import com.kursor.chronicles_of_ww2.dto.notes.NoteResponse
import com.kursor.chronicles_of_ww2.features.notes.domain.Note
import com.kursor.chronicles_of_ww2.features.notes.domain.NoteId

fun NoteResponse.toDomain() = Note(
    id = NoteId(id),
    title = title,
    content = content,
)