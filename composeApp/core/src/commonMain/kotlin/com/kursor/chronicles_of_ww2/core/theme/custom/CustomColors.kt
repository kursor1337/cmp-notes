package com.kursor.chronicles_of_ww2.core.theme.custom

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class CustomColors(
    val isLight: Boolean,
    val background: BackgroundColors,
    val text: TextColors,
    val icon: IconColors,
    val button: ButtonColors,
    val border: BorderColors
)

data class BackgroundColors(
    val screen: Color,
    val toast: Color
)

data class TextColors(
    val primary: Color,
    val invert: Color,
    val hint: Color,
    val error: Color
)

data class IconColors(
    val primary: Color,
    val error: Color
)

data class ButtonColors(
    val primary: Color,
    val secondary: Color
)

data class BorderColors(
    val primary: Color,
    val error: Color
)

val LocalCustomColors = staticCompositionLocalOf<CustomColors?> { null }