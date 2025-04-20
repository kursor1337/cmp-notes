package com.kursor.chronicles_of_ww2.features.notes.presentation.create_edit

import com.kursor.chronicles_of_ww2.features.notes.presentation.details.NoteDetailsComponent.Output
import kotlinx.coroutines.flow.StateFlow
import ru.mobileup.kmm_form_validation.control.InputControl

interface NoteCreateEditComponent {

    val titleInputControl: InputControl
    val contentInputControl: InputControl

    val isSavingInProgress: StateFlow<Boolean>

    fun onSaveClick()

    fun onBack()

    sealed interface Output {
        data object NoteSaved : Output
        data object BackRequested : Output
    }
}