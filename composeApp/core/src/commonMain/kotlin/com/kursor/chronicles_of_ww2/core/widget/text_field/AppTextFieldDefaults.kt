package com.kursor.chronicles_of_ww2.core.widget.text_field

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.kursor.chronicles_of_ww2.core.theme.custom.CustomTheme

@Immutable
enum class TextFieldType {
    Common,
    Secure
}

@Immutable
object AppTextFieldDefaults {

    const val CURSOR_ANIMATION_DURATION = 800

    @Stable
    val shape = RoundedCornerShape(16.dp)

    @Stable
    val textStyle: TextStyle
        @Composable
        get() = CustomTheme.typography.body.regular

    @Stable
    val labelStyle: TextStyle
        @Composable
        get() = CustomTheme.typography.body.regular

    @Stable
    val colors: TextFieldColors
        @Composable
        get() = TextFieldDefaults.colors(
            focusedTextColor = CustomTheme.colors.text.primary,
            unfocusedTextColor = CustomTheme.colors.text.primary,
            disabledTextColor = CustomTheme.colors.text.primary,
            errorTextColor = CustomTheme.colors.text.primary,

            focusedContainerColor = CustomTheme.colors.background.screen,
            unfocusedContainerColor = CustomTheme.colors.background.screen,
            disabledContainerColor = CustomTheme.colors.background.screen,
            errorContainerColor = CustomTheme.colors.background.screen,

            focusedPlaceholderColor = CustomTheme.colors.text.hint,
            unfocusedPlaceholderColor = CustomTheme.colors.text.hint,
            disabledPlaceholderColor = CustomTheme.colors.text.hint,
            errorPlaceholderColor = CustomTheme.colors.text.error,

            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,

            focusedSupportingTextColor = CustomTheme.colors.text.primary,
            unfocusedSupportingTextColor = CustomTheme.colors.text.primary,
            disabledSupportingTextColor = CustomTheme.colors.text.primary,
            errorSupportingTextColor = CustomTheme.colors.text.error,

            focusedLabelColor = CustomTheme.colors.text.hint,
            unfocusedLabelColor = CustomTheme.colors.text.hint,
            disabledLabelColor = CustomTheme.colors.text.hint,
            errorLabelColor = CustomTheme.colors.text.error,

            focusedPrefixColor = CustomTheme.colors.text.primary,
            unfocusedPrefixColor = CustomTheme.colors.text.primary,
            disabledPrefixColor = CustomTheme.colors.text.primary,
            errorPrefixColor = CustomTheme.colors.text.error,

            focusedSuffixColor = CustomTheme.colors.text.primary,
            unfocusedSuffixColor = CustomTheme.colors.text.primary,
            disabledSuffixColor = CustomTheme.colors.text.primary,
            errorSuffixColor = CustomTheme.colors.text.error,

            focusedLeadingIconColor = CustomTheme.colors.icon.primary,
            unfocusedLeadingIconColor = CustomTheme.colors.icon.primary,
            disabledLeadingIconColor = CustomTheme.colors.icon.primary,
            errorLeadingIconColor = CustomTheme.colors.icon.error,

            focusedTrailingIconColor = CustomTheme.colors.icon.primary,
            unfocusedTrailingIconColor = CustomTheme.colors.icon.primary,
            disabledTrailingIconColor = CustomTheme.colors.icon.primary,
            errorTrailingIconColor = CustomTheme.colors.icon.error,

            cursorColor = CustomTheme.colors.icon.primary,
            errorCursorColor = CustomTheme.colors.icon.error,

            selectionColors = TextSelectionColors(
                handleColor = CustomTheme.colors.icon.primary,
                backgroundColor = CustomTheme.colors.text.primary.copy(alpha = 0.4f)
            ),
        )

    @Stable
    private val defaultBorderWidth = 1.dp

    @Stable
    @Composable
    fun border(isError: Boolean, hasFocus: Boolean, width: Dp = defaultBorderWidth): BorderStroke {
        val color by animateColorAsState(
            targetValue = when {
                isError -> CustomTheme.colors.border.error
                hasFocus -> CustomTheme.colors.border.primary
                else -> CustomTheme.colors.border.primary
            },
            label = "animated border color"
        )
        return BorderStroke(width, color)
    }

    @Stable
    val defaultKeyboardActions: KeyboardActions
        @Composable
        get() = LocalFocusManager.current.run {
            KeyboardActions(onDone = { clearFocus() })
        }
}
