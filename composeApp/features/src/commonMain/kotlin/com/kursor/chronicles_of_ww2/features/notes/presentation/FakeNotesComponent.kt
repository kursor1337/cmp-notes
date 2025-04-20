package com.kursor.chronicles_of_ww2.features.notes.presentation

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.essenty.backhandler.BackHandler
import com.kursor.chronicles_of_ww2.core.utils.createFakeChildStackStateFlow
import com.kursor.chronicles_of_ww2.core.utils.fakeBackHandler
import com.kursor.chronicles_of_ww2.features.notes.presentation.list.FakeNoteListComponent
import kotlinx.coroutines.flow.StateFlow

class FakeNotesComponent : NotesComponent {
    override val childStack: StateFlow<ChildStack<*, NotesComponent.Child>> =
        createFakeChildStackStateFlow(
            NotesComponent.Child.List(FakeNoteListComponent())
        )

    override val backHandler: BackHandler = fakeBackHandler()

    override fun onBack() = Unit
}