package com.kursor.chronicles_of_ww2.features.auth.presentation.welcome

interface WelcomeComponent {
    fun onSignUpClick()
    fun onSignInClick()

    sealed interface Output {
        data object SignUpRequested : Output
        data object SignInRequested : Output
    }
}