package com.kursor.chronicles_of_ww2.core.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.kursor.chronicles_of_ww2.core.theme.custom.CustomTheme
import com.kursor.chronicles_of_ww2.core.theme.custom.toMaterialColors
import com.kursor.chronicles_of_ww2.core.theme.custom.toMaterialTypography

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkAppColors else LightAppColors
    val typography = AppTypography

    CustomTheme(
        colors = colorScheme,
        typography = typography
    ) {
        MaterialTheme(
            colorScheme = colorScheme.toMaterialColors(),
            typography = typography.toMaterialTypography(),
            content = content
        )
    }
}