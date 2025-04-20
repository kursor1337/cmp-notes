package com.kursor.chronicles_of_ww2.core.theme.custom

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle

data class CustomTypography(
    val title: TitleTypography,
    val body: BodyTypography,
    val button: ButtonTypography,
    val caption: CaptionTypography
)

data class TitleTypography(
    val heading: TextStyle,
    val mediumBold: TextStyle
)

data class BodyTypography(
    val regular: TextStyle
)

data class ButtonTypography(
    val bold: TextStyle
)

data class CaptionTypography(
    val regular: TextStyle
)

val LocalCustomTypography = staticCompositionLocalOf<CustomTypography?> { null }