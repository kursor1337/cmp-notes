package com.kursor.chronicles_of_ww2.features.notes.presentation.details

import com.arkivanov.decompose.ComponentContext
import com.kursor.chronicles_of_ww2.core.error_handling.ErrorHandler
import com.kursor.chronicles_of_ww2.core.utils.LoadableState
import com.kursor.chronicles_of_ww2.core.utils.observe
import com.kursor.chronicles_of_ww2.features.notes.domain.Note
import com.kursor.chronicles_of_ww2.features.notes.domain.NoteId
import com.kursor.chronicles_of_ww2.features.notes.domain.NoteRepository
import kotlinx.coroutines.flow.StateFlow
import me.aartikov.replica.algebra.normal.withKey

class RealNoteDetailsComponent(
    componentContext: ComponentContext,
    private val onOutput: (NoteDetailsComponent.Output) -> Unit,
    private val noteId: NoteId,
    noteRepository: NoteRepository,
    errorHandler: ErrorHandler
) : ComponentContext by componentContext, NoteDetailsComponent {

    private val noteReplica = noteRepository
        .noteByIdReplica
        .withKey(noteId)

    override val note: StateFlow<LoadableState<Note>> =
        noteReplica.observe(componentContext, errorHandler)

    override fun onEditClick() {
        val note = note.value.data ?: return
        onOutput(NoteDetailsComponent.Output.NoteEditRequested(note))
    }

    override fun onRetryClick() {
        noteReplica.refresh()
    }

    override fun onBack() {
        onOutput(NoteDetailsComponent.Output.BackRequested)
    }
}