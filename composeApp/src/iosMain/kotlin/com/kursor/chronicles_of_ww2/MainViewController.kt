package com.kursor.chronicles_of_ww2

import androidx.compose.ui.window.ComposeUIViewController
import com.kursor.chronicles_of_ww2.features.root.presentation.RootComponent
import com.kursor.chronicles_of_ww2.features.root.presentation.RootUi

fun MainViewController(
    rootComponent: RootComponent
) = ComposeUIViewController {
    RootUi(rootComponent)
}