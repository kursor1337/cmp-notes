package com.kursor.chronicles_of_ww2.core.widget

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kursor.chronicles_of_ww2.core.theme.custom.CustomTheme

@Composable
fun AppToolbar(
    modifier: Modifier = Modifier,
    title: String? = null,
    navigationIcon: @Composable (() -> Unit)? = null,
    actionIcon: @Composable (() -> Unit)? = null
) {
    Row(modifier = modifier) {
        navigationIcon?.invoke()

        title?.let {
            Text(
                text = it,
                style = CustomTheme.typography.title.heading,
                color = CustomTheme.colors.text.primary,
                modifier = Modifier.weight(1f)
            )
        }

        actionIcon?.invoke()
    }
}