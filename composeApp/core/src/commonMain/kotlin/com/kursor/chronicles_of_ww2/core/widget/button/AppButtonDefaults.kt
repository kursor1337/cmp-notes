package ru.mobileup.samples.core.widget.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.kursor.chronicles_of_ww2.core.theme.custom.CustomTheme

@Immutable
enum class ButtonType {
    Primary,
    Secondary
}

@Immutable
object AppButtonDefaults {

    @Stable
    val buttonShape = RoundedCornerShape(16.dp)

    @Stable
    val contentPadding = PaddingValues(16.dp)

    @Stable
    val textStyle: TextStyle
        @Composable
        get() = CustomTheme.typography.button.bold

    @Stable
    @Composable
    fun colors(buttonType: ButtonType): ButtonColors = ButtonColors(
        containerColor = containerColor(buttonType),
        contentColor = contentColor(buttonType),
        disabledContainerColor = disabledContainerColor(buttonType),
        disabledContentColor = disabledContentColor(buttonType),
    )

    @Stable
    @Composable
    fun containerColor(buttonType: ButtonType): Color = when (buttonType) {
        ButtonType.Primary -> CustomTheme.colors.button.primary
        ButtonType.Secondary -> CustomTheme.colors.button.secondary
    }

    @Stable
    @Composable
    fun contentColor(buttonType: ButtonType): Color = when (buttonType) {
        ButtonType.Primary -> CustomTheme.colors.text.primary
        ButtonType.Secondary -> CustomTheme.colors.text.primary
    }

    @Stable
    @Composable
    fun disabledContainerColor(buttonType: ButtonType): Color = when (buttonType) {
        ButtonType.Primary -> CustomTheme.colors.button.primary
        ButtonType.Secondary -> CustomTheme.colors.button.primary
    }

    @Stable
    @Composable
    fun disabledContentColor(buttonType: ButtonType): Color = when (buttonType) {
        ButtonType.Primary -> CustomTheme.colors.text.primary
        ButtonType.Secondary -> CustomTheme.colors.text.primary
    }

    @Stable
    @Composable
    fun progressIndicatorColor(buttonType: ButtonType): Color = when (buttonType) {
        ButtonType.Primary -> CustomTheme.colors.text.primary
        ButtonType.Secondary -> CustomTheme.colors.text.primary
    }

    @Stable
    @Composable
    fun border(buttonType: ButtonType, isEnabled: Boolean): BorderStroke {
        return BorderStroke(0.dp, Color.Transparent)
    }
}
