package com.kursor.chronicles_of_ww2.features.notes.presentation.create_edit

import com.arkivanov.decompose.ComponentContext
import com.kursor.chronicles_of_ww2.core.error_handling.ErrorHandler
import com.kursor.chronicles_of_ww2.core.error_handling.safeLaunch
import com.kursor.chronicles_of_ww2.core.state.withProgress
import com.kursor.chronicles_of_ww2.core.utils.InputControl
import com.kursor.chronicles_of_ww2.core.utils.componentScope
import com.kursor.chronicles_of_ww2.features.notes.domain.Note
import com.kursor.chronicles_of_ww2.features.notes.domain.NoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import ru.mobileup.kmm_form_validation.control.InputControl

class RealNoteCreateEditComponent(
    componentContext: ComponentContext,
    private val onOutput: (NoteCreateEditComponent.Output) -> Unit,
    private val note: Note?,
    private val noteRepository: NoteRepository,
    private val errorHandler: ErrorHandler
) : ComponentContext by componentContext, NoteCreateEditComponent {

    override val titleInputControl: InputControl = InputControl(
        initialText = note?.title.orEmpty()
    )

    override val contentInputControl: InputControl = InputControl(
        initialText = note?.content.orEmpty()
    )

    override val isSavingInProgress = MutableStateFlow(false)

    override fun onSaveClick() {
        componentScope.safeLaunch(errorHandler) {
            withProgress(isSavingInProgress) {
                if (note != null) {
                    noteRepository.editNote(
                        noteId = note.id,
                        title = titleInputControl.text.value,
                        content = contentInputControl.text.value
                    )
                } else {
                    noteRepository.createNote(
                        title = titleInputControl.text.value,
                        content = contentInputControl.text.value
                    )
                }
                noteRepository.invalidateNotes()
                noteRepository.noteListReplica.getData() // wait till data updates
                onOutput(NoteCreateEditComponent.Output.NoteSaved)
            }
        }
    }

    override fun onBack() {
        onOutput(NoteCreateEditComponent.Output.BackRequested)
    }
}