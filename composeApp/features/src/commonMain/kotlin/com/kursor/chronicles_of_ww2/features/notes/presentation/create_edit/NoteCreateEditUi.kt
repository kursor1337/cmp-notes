package com.kursor.chronicles_of_ww2.features.notes.presentation.create_edit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cmp_notes.composeapp.features.generated.resources.Res
import cmp_notes.composeapp.features.generated.resources.note_create_edit_button_save
import cmp_notes.composeapp.features.generated.resources.note_create_edit_input_content
import cmp_notes.composeapp.features.generated.resources.note_create_edit_input_title
import com.kursor.chronicles_of_ww2.core.widget.AppToolbar
import com.kursor.chronicles_of_ww2.core.widget.BackButton
import com.kursor.chronicles_of_ww2.core.widget.FullscreenCircularProgress
import com.kursor.chronicles_of_ww2.core.widget.button.AppButton
import com.kursor.chronicles_of_ww2.core.widget.text_field.AppTextField
import org.jetbrains.compose.resources.stringResource
import ru.mobileup.samples.core.widget.button.ButtonType

@Composable
fun NoteCreateEditUi(
    component: NoteCreateEditComponent,
    modifier: Modifier = Modifier
) {

    val isSavingInProgress by component.isSavingInProgress.collectAsState()
    Scaffold(
        modifier = modifier,
        topBar = {
            AppToolbar(
                navigationIcon = { BackButton(onBack = component::onBack) },
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(20.dp)
        ) {
            AppTextField(
                inputControl = component.titleInputControl,
                placeholder = stringResource(Res.string.note_create_edit_input_title)
            )
            Spacer(Modifier.height(8.dp))
            AppTextField(
                inputControl = component.contentInputControl,
                placeholder = stringResource(Res.string.note_create_edit_input_content),
                modifier = Modifier.weight(1f)
            )
            Spacer(Modifier.height(20.dp))
            AppButton(
                buttonType = ButtonType.Primary,
                onClick = component::onSaveClick,
                text = stringResource(Res.string.note_create_edit_button_save),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }

    if (isSavingInProgress) {
        FullscreenCircularProgress(overlay = true)
    }

}