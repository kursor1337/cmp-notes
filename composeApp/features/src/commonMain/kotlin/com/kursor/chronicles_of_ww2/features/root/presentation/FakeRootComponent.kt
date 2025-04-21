package com.kursor.chronicles_of_ww2.features.root.presentation

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.essenty.backhandler.BackHandler
import com.kursor.chronicles_of_ww2.core.message.presentation.FakeMessageComponent
import com.kursor.chronicles_of_ww2.core.utils.createFakeChildStackStateFlow
import com.kursor.chronicles_of_ww2.core.utils.fakeBackHandler
import com.kursor.chronicles_of_ww2.features.auth.presentation.FakeAuthComponent
import kotlinx.coroutines.flow.StateFlow

class FakeRootComponent : RootComponent {
    override val childStack: StateFlow<ChildStack<*, RootComponent.Child>> =
        createFakeChildStackStateFlow(
            RootComponent.Child.Auth(FakeAuthComponent())
        )

    override val messageComponent = FakeMessageComponent()
    override val backHandler: BackHandler = fakeBackHandler()

    override fun onBack() = Unit
}