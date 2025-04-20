package com.kursor.chronicles_of_ww2.features.notes

import com.arkivanov.decompose.ComponentContext
import com.kursor.chronicles_of_ww2.core.ComponentFactory
import com.kursor.chronicles_of_ww2.core.network.NetworkApiFactory
import com.kursor.chronicles_of_ww2.features.notes.data.NoteApi
import com.kursor.chronicles_of_ww2.features.notes.data.NoteRepositoryImpl
import com.kursor.chronicles_of_ww2.features.notes.data.createNoteApi
import com.kursor.chronicles_of_ww2.features.notes.domain.Note
import com.kursor.chronicles_of_ww2.features.notes.domain.NoteId
import com.kursor.chronicles_of_ww2.features.notes.domain.NoteRepository
import com.kursor.chronicles_of_ww2.features.notes.presentation.NotesComponent
import com.kursor.chronicles_of_ww2.features.notes.presentation.RealNotesComponent
import com.kursor.chronicles_of_ww2.features.notes.presentation.create_edit.NoteCreateEditComponent
import com.kursor.chronicles_of_ww2.features.notes.presentation.create_edit.RealNoteCreateEditComponent
import com.kursor.chronicles_of_ww2.features.notes.presentation.details.NoteDetailsComponent
import com.kursor.chronicles_of_ww2.features.notes.presentation.details.RealNoteDetailsComponent
import com.kursor.chronicles_of_ww2.features.notes.presentation.list.NoteListComponent
import com.kursor.chronicles_of_ww2.features.notes.presentation.list.RealNoteListComponent
import org.koin.core.component.get
import org.koin.dsl.module

val notesModule = module {
    single<NoteApi> { get<NetworkApiFactory>().authorizedKtorfit.createNoteApi() }
    single<NoteRepository> { NoteRepositoryImpl(get(), get()) }
}

fun ComponentFactory.createNotesComponent(
    componentContext: ComponentContext,
): NotesComponent {
    return RealNotesComponent(
        componentContext,
        get()
    )
}

fun ComponentFactory.createNoteListComponent(
    componentContext: ComponentContext,
    onOutput: (NoteListComponent.Output) -> Unit
): NoteListComponent {
    return RealNoteListComponent(
        componentContext,
        onOutput,
        get(),
        get()
    )
}

fun ComponentFactory.createNoteDetailsComponent(
    componentContext: ComponentContext,
    onOutput: (NoteDetailsComponent.Output) -> Unit,
    noteId: NoteId
): NoteDetailsComponent {
    return RealNoteDetailsComponent(
        componentContext,
        onOutput,
        noteId,
        get(),
        get()
    )
}

fun ComponentFactory.createNoteCreateEditComponent(
    componentContext: ComponentContext,
    onOutput: (NoteCreateEditComponent.Output) -> Unit,
    note: Note?
): NoteCreateEditComponent {
    return RealNoteCreateEditComponent(
        componentContext,
        onOutput,
        note,
        get(),
        get()
    )
}