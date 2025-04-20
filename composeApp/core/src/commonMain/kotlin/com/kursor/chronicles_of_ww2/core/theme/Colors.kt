package com.kursor.chronicles_of_ww2.core.theme

import androidx.compose.ui.graphics.Color
import com.kursor.chronicles_of_ww2.core.theme.custom.BackgroundColors
import com.kursor.chronicles_of_ww2.core.theme.custom.BorderColors
import com.kursor.chronicles_of_ww2.core.theme.custom.ButtonColors
import com.kursor.chronicles_of_ww2.core.theme.custom.CustomColors
import com.kursor.chronicles_of_ww2.core.theme.custom.IconColors
import com.kursor.chronicles_of_ww2.core.theme.custom.TextColors

val LightAppColors = CustomColors(
    isLight = true,
    background = BackgroundColors(
        screen = Color(0xFFFAFAFA),
        toast = Color(0xFF222222)
    ),
    text = TextColors(
        primary = Color(0xFF000000),
        invert = Color(0xFFFFFFFF),
        hint = Color(0xFFD0BEA6),
        error = Color(0xFFFF0000)
    ),
    icon = IconColors(
        primary = Color(0xFF000000),
        error = Color(0xFFFF0000)
    ),
    button = ButtonColors(
        primary = Color(0xFFF0DEC6),
        secondary = Color(0xFFEDEEF0),
    ),
    border = BorderColors(
        primary = Color(0xFF000000),
        error = Color(0xFFFF0000)
    )
)

val DarkAppColors = LightAppColors