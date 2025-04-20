package com.kursor.chronicles_of_ww2.features.notes.presentation.list

import com.kursor.chronicles_of_ww2.core.utils.LoadableState
import com.kursor.chronicles_of_ww2.features.notes.domain.Note
import com.kursor.chronicles_of_ww2.features.notes.domain.NoteId
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FakeNoteListComponent : NoteListComponent {
    override val notes: StateFlow<LoadableState<List<Note>>> =
        MutableStateFlow(LoadableState(data = emptyList()))

    override fun onNoteClick(id: NoteId) = Unit
    override fun onNewNoteClick() = Unit
    override fun onRetryClick() = Unit
    override fun onRefresh() = Unit
}