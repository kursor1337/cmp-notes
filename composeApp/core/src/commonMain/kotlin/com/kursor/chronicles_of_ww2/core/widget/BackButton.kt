package com.kursor.chronicles_of_ww2.core.widget

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import cmp_notes.composeapp.core.generated.resources.Res
import cmp_notes.composeapp.core.generated.resources.ic_24_arrow_back
import com.arkivanov.essenty.backhandler.BackDispatcher
import org.jetbrains.compose.resources.painterResource

@Composable
fun BackButton(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    painter: Painter = painterResource(Res.drawable.ic_24_arrow_back),
    onClick: (() -> Unit)? = null
) {
    IconButton(
        modifier = modifier,
        onClick = {
            if (onClick != null) {
                onClick()
            } else {
                println("BackButton onBack")
                onBack()
            }
        }
    ) {
        Icon(painter, contentDescription = null)
    }
}