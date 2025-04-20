package com.kursor.chronicles_of_ww2.features.auth.presentation.welcome

import com.arkivanov.decompose.ComponentContext

class RealWelcomeComponent(
    componentContext: ComponentContext,
    private val onOutput: (WelcomeComponent.Output) -> Unit,
) : WelcomeComponent {

    override fun onSignUpClick() {
        onOutput(WelcomeComponent.Output.SignUpRequested)
    }

    override fun onSignInClick() {
        onOutput(WelcomeComponent.Output.SignInRequested)
    }
}