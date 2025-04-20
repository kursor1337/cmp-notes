package com.kursor.chronicles_of_ww2.features.notes.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.slot.childSlot
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.pushNew
import com.kursor.chronicles_of_ww2.core.ComponentFactory
import com.kursor.chronicles_of_ww2.core.utils.toStateFlow
import com.kursor.chronicles_of_ww2.features.notes.createNoteCreateEditComponent
import com.kursor.chronicles_of_ww2.features.notes.createNoteDetailsComponent
import com.kursor.chronicles_of_ww2.features.notes.createNoteListComponent
import com.kursor.chronicles_of_ww2.features.notes.domain.Note
import com.kursor.chronicles_of_ww2.features.notes.domain.NoteId
import com.kursor.chronicles_of_ww2.features.notes.presentation.create_edit.NoteCreateEditComponent
import com.kursor.chronicles_of_ww2.features.notes.presentation.details.NoteDetailsComponent
import com.kursor.chronicles_of_ww2.features.notes.presentation.list.NoteListComponent
import kotlinx.coroutines.flow.StateFlow
import kotlinx.serialization.Serializable

class RealNotesComponent(
    componentContext: ComponentContext,
    private val componentFactory: ComponentFactory
) : ComponentContext by componentContext, NotesComponent {

    private val navigation = StackNavigation<ChildConfig>()

    override val childStack: StateFlow<ChildStack<*, NotesComponent.Child>> = childStack(
        source = navigation,
        serializer = ChildConfig.serializer(),
        initialConfiguration = ChildConfig.List,
        handleBackButton = true,
        childFactory = ::createChild
    ).toStateFlow(lifecycle)

    override fun onBack() = navigation.pop()

    private fun createChild(
        childConfig: ChildConfig,
        componentContext: ComponentContext
    ) = when (childConfig) {
        is ChildConfig.CreateEdit -> NotesComponent.Child.CreateEdit(
            componentFactory.createNoteCreateEditComponent(
                componentContext,
                ::onCreateEditOutput,
                childConfig.note,
            )
        )
        is ChildConfig.Details -> NotesComponent.Child.Details(
            componentFactory.createNoteDetailsComponent(
                componentContext,
                ::onDetailsOutput,
                childConfig.noteId,
            )
        )
        ChildConfig.List -> NotesComponent.Child.List(
            componentFactory.createNoteListComponent(
                componentContext,
                ::onListOutput
            )
        )
    }

    private fun onCreateEditOutput(output: NoteCreateEditComponent.Output) {
        when (output) {
            NoteCreateEditComponent.Output.NoteSaved -> {
                navigation.pop()
            }

            NoteCreateEditComponent.Output.BackRequested -> navigation.pop()
        }
    }

    private fun onDetailsOutput(output: NoteDetailsComponent.Output) {
        when (output) {
            is NoteDetailsComponent.Output.NoteEditRequested -> {
                navigation.pushNew(ChildConfig.CreateEdit(output.note))
            }

            NoteDetailsComponent.Output.BackRequested -> navigation.pop()
        }
    }

    private fun onListOutput(output: NoteListComponent.Output) {
        when (output) {
            NoteListComponent.Output.NoteCreationRequested -> {
                navigation.pushNew(ChildConfig.CreateEdit(null))
            }
            is NoteListComponent.Output.NoteViewerRequested -> {
                navigation.pushNew(ChildConfig.Details(output.noteId))
            }
        }
    }

    @Serializable
    sealed interface ChildConfig {
        @Serializable
        data object List : ChildConfig

        @Serializable
        data class Details(val noteId: NoteId) : ChildConfig

        @Serializable
        data class CreateEdit(val note: Note?) : ChildConfig
    }
}