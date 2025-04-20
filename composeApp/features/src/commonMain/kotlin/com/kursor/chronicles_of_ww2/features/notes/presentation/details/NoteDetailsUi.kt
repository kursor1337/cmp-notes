package com.kursor.chronicles_of_ww2.features.notes.presentation.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cmp_notes.composeapp.features.generated.resources.Res
import cmp_notes.composeapp.features.generated.resources.note_details_button_edit
import com.kursor.chronicles_of_ww2.core.theme.custom.CustomTheme
import com.kursor.chronicles_of_ww2.core.widget.AppToolbar
import com.kursor.chronicles_of_ww2.core.widget.BackButton
import com.kursor.chronicles_of_ww2.core.widget.LceWidget
import com.kursor.chronicles_of_ww2.core.widget.button.AppButton
import org.jetbrains.compose.resources.stringResource
import ru.mobileup.samples.core.widget.button.ButtonType

@Composable
fun NoteDetailsUi(
    component: NoteDetailsComponent,
    modifier: Modifier = Modifier
) {
    val note by component.note.collectAsState()
    Scaffold(
        modifier = modifier,
        topBar = {
            AppToolbar(
                title = note.data?.title ?: "",
                navigationIcon = { BackButton(onBack = component::onBack) },
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
    ) { paddingValues ->
        LceWidget(
            modifier = Modifier.padding(paddingValues),
            state = note,
            onRetryClick = component::onRetryClick
        ) { data, _ ->
            Column(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .padding(top = 20.dp)
            ) {
                Text(
                    text = data.content,
                    style = CustomTheme.typography.body.regular,
                    color = CustomTheme.colors.text.primary,
                    modifier = Modifier.weight(1f)
                )
                Spacer(Modifier.height(16.dp))
                AppButton(
                    buttonType = ButtonType.Primary,
                    onClick = component::onEditClick,
                    text = stringResource(Res.string.note_details_button_edit),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}