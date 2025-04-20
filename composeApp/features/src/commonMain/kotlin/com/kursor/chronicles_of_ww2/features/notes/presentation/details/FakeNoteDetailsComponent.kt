package com.kursor.chronicles_of_ww2.features.notes.presentation.details

import com.kursor.chronicles_of_ww2.core.utils.LoadableState
import com.kursor.chronicles_of_ww2.features.notes.domain.Note
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FakeNoteDetailsComponent : NoteDetailsComponent {
    override val note: StateFlow<LoadableState<Note>> = MutableStateFlow(
        LoadableState(data = Note.MOCK)
    )

    override fun onEditClick() = Unit
    override fun onRetryClick() = Unit
    override fun onBack() = Unit
}