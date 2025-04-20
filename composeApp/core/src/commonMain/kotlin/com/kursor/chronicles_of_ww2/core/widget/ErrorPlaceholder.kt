package com.kursor.chronicles_of_ww2.core.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cmp_notes.composeapp.core.generated.resources.Res
import cmp_notes.composeapp.core.generated.resources.common_retry
import com.kursor.chronicles_of_ww2.core.theme.AppTheme
import com.kursor.chronicles_of_ww2.core.theme.custom.CustomTheme
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ErrorPlaceholder(
    errorMessage: String,
    onRetryClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxSize()
    ) {
        Text(
            text = errorMessage,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
            style = CustomTheme.typography.body.regular
        )
        TextButton(
            onClick = onRetryClick
        ) {
            Text(
                text = stringResource(Res.string.common_retry).uppercase()
            )
        }
    }
}

@Preview
@Composable
fun ErrorPlaceholderPreview() {
    AppTheme {
        ErrorPlaceholder(
            errorMessage = "Error message",
            onRetryClick = {}
        )
    }
}