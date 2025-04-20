package com.kursor.chronicles_of_ww2.features.notes.presentation.list

import com.arkivanov.decompose.ComponentContext
import com.kursor.chronicles_of_ww2.core.error_handling.ErrorHandler
import com.kursor.chronicles_of_ww2.core.utils.LoadableState
import com.kursor.chronicles_of_ww2.core.utils.observe
import com.kursor.chronicles_of_ww2.features.notes.domain.Note
import com.kursor.chronicles_of_ww2.features.notes.domain.NoteId
import com.kursor.chronicles_of_ww2.features.notes.domain.NoteRepository
import kotlinx.coroutines.flow.StateFlow

class RealNoteListComponent(
    componentContext: ComponentContext,
    private val onOutput: (NoteListComponent.Output) -> Unit,
    noteRepository: NoteRepository,
    errorHandler: ErrorHandler
) : ComponentContext by componentContext, NoteListComponent {

    private val noteListReplica = noteRepository.noteListReplica

    override val notes: StateFlow<LoadableState<List<Note>>> =
        noteListReplica.observe(componentContext, errorHandler)

    override fun onNoteClick(id: NoteId) {
        onOutput(NoteListComponent.Output.NoteViewerRequested(id))
    }

    override fun onNewNoteClick() {
        onOutput(NoteListComponent.Output.NoteCreationRequested)
    }

    override fun onRetryClick() {
        noteListReplica.refresh()
    }

    override fun onRefresh() {
        noteListReplica.refresh()
    }
}