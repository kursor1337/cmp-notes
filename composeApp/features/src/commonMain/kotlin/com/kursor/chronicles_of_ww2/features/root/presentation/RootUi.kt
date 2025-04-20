package com.kursor.chronicles_of_ww2.features.root.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.kursor.chronicles_of_ww2.core.message.ui.MessageUi
import com.kursor.chronicles_of_ww2.core.theme.AppTheme
import com.kursor.chronicles_of_ww2.core.utils.predictiveBackAnimation
import com.kursor.chronicles_of_ww2.features.auth.presentation.AuthUi
import com.kursor.chronicles_of_ww2.features.notes.presentation.NotesUi
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalDecomposeApi::class)
@Composable
fun RootUi(
    component: RootComponent,
    modifier: Modifier = Modifier
) {
    val childStack by component.childStack.collectAsState()

    Children(
        stack = childStack,
        modifier = modifier,
        animation = component.predictiveBackAnimation()
    ) { child ->
        when (val instance = child.instance) {
            is RootComponent.Child.Auth -> AuthUi(instance.component)
            is RootComponent.Child.Notes -> NotesUi(instance.component)
        }
    }

    MessageUi(
        component = component.messageComponent,
        modifier = modifier,
        bottomPadding = 16.dp
    )
}

@Preview
@Composable
fun RootUiPreview() {
    AppTheme {
        RootUi(FakeRootComponent())
    }
}