package com.kursor.chronicles_of_ww2.features.notes.domain

import me.aartikov.replica.keyed.KeyedReplica
import me.aartikov.replica.single.Replica

interface NoteRepository {

    val noteListReplica: Replica<List<Note>>
    val noteByIdReplica: KeyedReplica<NoteId, Note>

    suspend fun createNote(title: String, content: String)

    suspend fun editNote(noteId: NoteId, title: String, content: String)

    suspend fun invalidateNotes()
}