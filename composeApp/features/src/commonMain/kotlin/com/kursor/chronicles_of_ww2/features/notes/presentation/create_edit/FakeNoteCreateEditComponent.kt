package com.kursor.chronicles_of_ww2.features.notes.presentation.create_edit

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import ru.mobileup.kmm_form_validation.control.InputControl

@OptIn(DelicateCoroutinesApi::class)
class FakeNoteCreateEditComponent : NoteCreateEditComponent {
    override val titleInputControl: InputControl = InputControl(GlobalScope)
    override val contentInputControl: InputControl = InputControl(GlobalScope)
    override val isSavingInProgress = MutableStateFlow(false)

    override fun onSaveClick() = Unit
    override fun onBack() = Unit
}