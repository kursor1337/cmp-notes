package com.kursor.chronicles_of_ww2.core.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.kursor.chronicles_of_ww2.core.theme.custom.CustomTheme

@Composable
fun FullscreenCircularProgress(
    modifier: Modifier = Modifier,
    overlay: Boolean = false
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                if (overlay) {
                    CustomTheme.colors.background.screen.copy(alpha = 0.7f)
                } else {
                    CustomTheme.colors.background.screen
                }
            )
        ,
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}
