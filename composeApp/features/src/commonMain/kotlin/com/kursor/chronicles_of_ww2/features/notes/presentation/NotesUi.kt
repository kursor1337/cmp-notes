package com.kursor.chronicles_of_ww2.features.notes.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.kursor.chronicles_of_ww2.core.utils.predictiveBackAnimation
import com.kursor.chronicles_of_ww2.features.notes.presentation.create_edit.NoteCreateEditUi
import com.kursor.chronicles_of_ww2.features.notes.presentation.details.NoteDetailsUi
import com.kursor.chronicles_of_ww2.features.notes.presentation.list.NoteListUi

@OptIn(ExperimentalDecomposeApi::class)
@Composable
fun NotesUi(
    component: NotesComponent,
    modifier: Modifier = Modifier
) {
    val childStack by component.childStack.collectAsState()

    Children(
        stack = childStack,
        modifier = modifier,
        animation = component.predictiveBackAnimation()
    ) { child ->
        when (val instance = child.instance) {
            is NotesComponent.Child.CreateEdit -> NoteCreateEditUi(instance.component)
            is NotesComponent.Child.Details -> NoteDetailsUi(instance.component)
            is NotesComponent.Child.List -> NoteListUi(instance.component)
        }
    }
}