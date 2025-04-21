package com.kursor.chronicles_of_ww2.features.notes.presentation

import com.arkivanov.decompose.router.stack.ChildStack
import com.kursor.chronicles_of_ww2.core.utils.PredictiveBackComponent
import com.kursor.chronicles_of_ww2.features.notes.presentation.create_edit.NoteCreateEditComponent
import com.kursor.chronicles_of_ww2.features.notes.presentation.details.NoteDetailsComponent
import com.kursor.chronicles_of_ww2.features.notes.presentation.list.NoteListComponent
import kotlinx.coroutines.flow.StateFlow

interface NotesComponent : PredictiveBackComponent {

    val childStack: StateFlow<ChildStack<*, Child>>

    sealed interface Child {
        data class List(val component: NoteListComponent) : Child
        data class Details(val component: NoteDetailsComponent) : Child
        data class CreateEdit(val component: NoteCreateEditComponent) : Child
    }
}