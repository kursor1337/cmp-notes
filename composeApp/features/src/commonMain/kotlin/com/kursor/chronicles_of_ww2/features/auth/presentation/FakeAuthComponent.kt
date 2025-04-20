package com.kursor.chronicles_of_ww2.features.auth.presentation

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.essenty.backhandler.BackHandler
import com.kursor.chronicles_of_ww2.core.utils.createFakeChildStackStateFlow
import com.kursor.chronicles_of_ww2.core.utils.fakeBackHandler
import com.kursor.chronicles_of_ww2.features.auth.presentation.welcome.FakeWelcomeComponent
import kotlinx.coroutines.flow.StateFlow

class FakeAuthComponent : AuthComponent {
    override val childStack: StateFlow<ChildStack<*, AuthComponent.Child>> =
        createFakeChildStackStateFlow(
            AuthComponent.Child.Welcome(FakeWelcomeComponent())
        )

    override val backHandler: BackHandler = fakeBackHandler()

    override fun onBack() = Unit
}