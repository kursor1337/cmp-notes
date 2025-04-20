package com.kursor.chronicles_of_ww2.features.notes.data

import com.kursor.chronicles_of_ww2.dto.notes.CreateNoteRequest
import com.kursor.chronicles_of_ww2.dto.notes.EditNoteRequest
import com.kursor.chronicles_of_ww2.features.notes.domain.Note
import com.kursor.chronicles_of_ww2.features.notes.domain.NoteId
import com.kursor.chronicles_of_ww2.features.notes.domain.NoteRepository
import me.aartikov.replica.algebra.normal.associate
import me.aartikov.replica.algebra.normal.map
import me.aartikov.replica.client.ReplicaClient
import me.aartikov.replica.keyed.KeyedPhysicalReplica
import me.aartikov.replica.keyed.KeyedReplica
import me.aartikov.replica.keyed.KeyedReplicaSettings
import me.aartikov.replica.single.PhysicalReplica
import me.aartikov.replica.single.Replica
import me.aartikov.replica.single.ReplicaSettings
import kotlin.time.Duration.Companion.minutes

class NoteRepositoryImpl(
    replicaClient: ReplicaClient,
    private val noteApi: NoteApi
) : NoteRepository {
    override val noteListReplica: PhysicalReplica<List<Note>> =
        replicaClient.createReplica(
            name = "noteListReplica",
            settings = ReplicaSettings(staleTime = 5.minutes),
            fetcher = {
                noteApi.getAllNotes().map { it.toDomain() }
            }
        )
    override val noteByIdReplica: KeyedReplica<NoteId, Note> = associate { noteId ->
        noteListReplica.map { notes ->
            notes
                .find { it.id ==  noteId}
                ?: error("Note with id = ${noteId.value} not found")
        }
    }

    override suspend fun createNote(title: String, content: String) {
        noteApi.createNote(CreateNoteRequest(title, content))
    }

    override suspend fun editNote(noteId: NoteId, title: String, content: String) {
        noteApi.editNote(EditNoteRequest(noteId.value, title, content))
    }

    override suspend fun invalidateNotes() {
        noteListReplica.invalidate()
    }
}