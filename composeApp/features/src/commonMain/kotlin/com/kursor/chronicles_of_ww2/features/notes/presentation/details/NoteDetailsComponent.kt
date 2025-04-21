package com.kursor.chronicles_of_ww2.features.notes.presentation.details

import com.kursor.chronicles_of_ww2.core.utils.LoadableState
import com.kursor.chronicles_of_ww2.features.notes.domain.Note
import kotlinx.coroutines.flow.StateFlow

interface NoteDetailsComponent {

    val note: StateFlow<LoadableState<Note>>

    fun onEditClick()

    fun onRetryClick()

    fun onBack()

    sealed interface Output {
        data class NoteEditRequested(val note: Note) : Output
        data object BackRequested : Output
    }
}