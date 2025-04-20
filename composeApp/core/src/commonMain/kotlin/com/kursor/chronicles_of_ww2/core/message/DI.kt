package com.kursor.chronicles_of_ww2.core.message

import com.arkivanov.decompose.ComponentContext
import com.kursor.chronicles_of_ww2.core.ComponentFactory
import com.kursor.chronicles_of_ww2.core.message.presentation.MessageComponent
import com.kursor.chronicles_of_ww2.core.message.presentation.RealMessageComponent
import org.koin.core.component.get

fun ComponentFactory.createMessageComponent(
    componentContext: ComponentContext
): MessageComponent {
    return RealMessageComponent(componentContext, get())
}