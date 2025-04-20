package com.kursor.chronicles_of_ww2.core.theme.custom

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme

fun CustomColors.toMaterialColors(): ColorScheme {
    return if (isLight) {
        lightColorScheme(
            primary = button.primary,
            onPrimary = text.primary,
            background = background.screen,
            surface = background.screen,
        )
    } else {
        darkColorScheme(
            primary = button.primary,
            onPrimary = text.primary,
            background = background.screen,
            surface = background.screen,
        )
    }
}

fun CustomTypography.toMaterialTypography(): Typography {
    return Typography(
        titleLarge = title.heading,
        titleMedium = title.mediumBold,
        titleSmall = title.mediumBold,
        bodyLarge = body.regular,
        bodyMedium = body.regular,
        bodySmall = body.regular,
        labelLarge = title.mediumBold,
        labelMedium = button.bold,
        labelSmall = button.bold
    )
}