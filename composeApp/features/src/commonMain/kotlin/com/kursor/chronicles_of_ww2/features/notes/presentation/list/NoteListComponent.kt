package com.kursor.chronicles_of_ww2.features.notes.presentation.list

import com.kursor.chronicles_of_ww2.core.utils.LoadableState
import com.kursor.chronicles_of_ww2.features.notes.domain.Note
import com.kursor.chronicles_of_ww2.features.notes.domain.NoteId
import kotlinx.coroutines.flow.StateFlow

interface NoteListComponent {

    val notes: StateFlow<LoadableState<List<Note>>>

    fun onNoteClick(id: NoteId)
    fun onNewNoteClick()
    fun onRetryClick()
    fun onRefresh()

    sealed interface Output {
        data object NoteCreationRequested : Output
        data class NoteViewerRequested(val noteId: NoteId) : Output
    }
}