package com.kursor.chronicles_of_ww2.core.message.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.kursor.chronicles_of_ww2.core.message.domain.Message
import com.kursor.chronicles_of_ww2.core.message.presentation.FakeMessageComponent
import com.kursor.chronicles_of_ww2.core.message.presentation.MessageComponent
import com.kursor.chronicles_of_ww2.core.strings.localized
import com.kursor.chronicles_of_ww2.core.theme.AppTheme
import com.kursor.chronicles_of_ww2.core.theme.custom.CustomTheme
import com.kursor.chronicles_of_ww2.core.utils.navigationBarsWithImePadding
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.math.roundToInt

/**
 * Displays a [Message] as a popup at the bottom of screen.
 */
@Composable
fun MessageUi(
    component: MessageComponent,
    modifier: Modifier = Modifier,
    bottomPadding: Dp
) {
    val visibleMessage by component.visibleMessage.collectAsState()

    val bottomPaddingPx = with(LocalDensity.current) { bottomPadding.toPx().roundToInt() }
    val additionalBottomPaddingPx = ((LocalMessageOffsets.current.values.maxOrNull() ?: 0))

    Box(
        modifier = modifier
            .fillMaxSize()
            .navigationBarsWithImePadding()
    ) {
        visibleMessage?.let {
            MessagePopup(
                message = it,
                bottomPaddingPx = bottomPaddingPx + additionalBottomPaddingPx,
                onAction = component::onActionClick
            )
        }
    }
}

@Composable
private fun MessagePopup(
    message: Message,
    onAction: () -> Unit,
    bottomPaddingPx: Int
) {
    Popup(
        offset = IntOffset(0, -bottomPaddingPx),
        alignment = Alignment.BottomCenter,
        properties = PopupProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        )
    ) {
        Card(
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = CustomTheme.colors.background.toast),
            elevation = CardDefaults.cardElevation(3.dp),
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp)
                .wrapContentSize()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .padding(vertical = 13.dp, horizontal = 16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = message.text.localized(),
                    color = CustomTheme.colors.text.invert,
                    style = CustomTheme.typography.body.regular
                )
                message.actionTitle?.let {
                    MessageButton(text = it.localized(), onClick = onAction)
                }
            }
        }
    }
}

@Composable
private fun MessageButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TextButton(
        onClick = onClick,
        modifier = modifier,
    ) {
        Text(
            text = text,
            style = CustomTheme.typography.button.bold
        )
    }
}

@Preview
@Composable
fun MessageUiPreview() {
    AppTheme {
        MessageUi(FakeMessageComponent(), Modifier, 40.dp)
    }
}