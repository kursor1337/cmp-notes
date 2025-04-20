package com.kursor.chronicles_of_ww2.notes

import com.kursor.chronicles_of_ww2.notes.data.NoteRepositoryImpl
import com.kursor.chronicles_of_ww2.notes.data.NoteService
import com.kursor.chronicles_of_ww2.notes.domain.NoteRepository
import org.koin.dsl.module

val notesModule = module {
    single<NoteService> { NoteService(get()) }
    single<NoteRepository> { NoteRepositoryImpl(get()) }
}