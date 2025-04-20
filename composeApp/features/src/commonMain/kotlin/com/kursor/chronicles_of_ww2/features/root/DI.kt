package com.kursor.chronicles_of_ww2.features.root

import com.arkivanov.decompose.ComponentContext
import com.kursor.chronicles_of_ww2.core.ComponentFactory
import com.kursor.chronicles_of_ww2.features.root.presentation.RealRootComponent
import com.kursor.chronicles_of_ww2.features.root.presentation.RootComponent
import org.koin.core.component.get

fun ComponentFactory.createRootComponent(componentContext: ComponentContext): RootComponent {
    return RealRootComponent(componentContext, get(), get())
}