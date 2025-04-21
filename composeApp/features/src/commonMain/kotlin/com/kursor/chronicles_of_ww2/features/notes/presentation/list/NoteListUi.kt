package com.kursor.chronicles_of_ww2.features.notes.presentation.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cmp_notes.composeapp.features.generated.resources.Res
import cmp_notes.composeapp.features.generated.resources.ic_24_add
import cmp_notes.composeapp.features.generated.resources.ic_24_arrow_forward
import cmp_notes.composeapp.features.generated.resources.note_list_title
import com.kursor.chronicles_of_ww2.core.theme.custom.CustomTheme
import com.kursor.chronicles_of_ww2.core.widget.AppToolbar
import com.kursor.chronicles_of_ww2.core.widget.PullRefreshLceWidget
import com.kursor.chronicles_of_ww2.features.notes.domain.Note
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun NoteListUi(
    component: NoteListComponent,
    modifier: Modifier = Modifier
) {
    val notes by component.notes.collectAsState()
    Scaffold(
        modifier = modifier,
        topBar = {
            AppToolbar(
                title = stringResource(Res.string.note_list_title),
                navigationIcon = null,
                modifier = Modifier.padding(20.dp)
            )
        },
        floatingActionButton = {
            NewNoteButton(component::onNewNoteClick)
        }
    ) { paddingValues ->
        PullRefreshLceWidget(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            state = notes,
            onRetryClick = component::onRetryClick,
            onRefresh = component::onRefresh
        ) { data, _ ->
            LazyColumn(
                modifier = Modifier
                    .padding(top = 20.dp)
            ) {
                items(
                    items = data,
                    key = { it.id.value }
                ) {
                    NoteItem(
                        note = it,
                        onClick = { component.onNoteClick(it.id) },
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun NoteItem(
    note: Note,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(
                horizontal = 20.dp,
                vertical = 8.dp
            )
            .clickable(onClick = onClick)
    ) {
        Text(
            text = note.title,
            style = CustomTheme.typography.title.mediumBold,
            color = CustomTheme.colors.text.primary,
            modifier = Modifier
                .weight(1f)
        )
        Icon(
            painter = painterResource(Res.drawable.ic_24_arrow_forward),
            contentDescription = null,
        )
    }
}

@Composable
fun NewNoteButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    FloatingActionButton(
        onClick = onClick,
        modifier = modifier,
        containerColor = CustomTheme.colors.button.primary,
        shape = CircleShape
    ) {
        Icon(
            painter = painterResource(Res.drawable.ic_24_add),
            contentDescription = null
        )
    }
}
