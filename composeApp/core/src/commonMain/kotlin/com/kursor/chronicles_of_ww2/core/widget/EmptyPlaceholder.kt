package com.kursor.chronicles_of_ww2.core.widget

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.kursor.chronicles_of_ww2.core.theme.AppTheme
import com.kursor.chronicles_of_ww2.core.theme.custom.CustomTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun EmptyPlaceholder(
    description: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()) // for pull to refresh
    ) {
        Text(
            text = description,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .align(Alignment.Center)
                .fillMaxWidth(),
            style = CustomTheme.typography.body.regular
        )
    }
}

@Preview
@Composable
fun EmptyPlaceholderPreview() {
    AppTheme {
        EmptyPlaceholder(description = "Description")
    }
}