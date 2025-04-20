package com.kursor.chronicles_of_ww2.features.auth.presentation

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.essenty.backhandler.BackHandlerOwner
import com.kursor.chronicles_of_ww2.core.utils.PredictiveBackComponent
import com.kursor.chronicles_of_ww2.features.auth.presentation.sign_in.SignInComponent
import com.kursor.chronicles_of_ww2.features.auth.presentation.sign_up.SignUpComponent
import com.kursor.chronicles_of_ww2.features.auth.presentation.welcome.WelcomeComponent
import kotlinx.coroutines.flow.StateFlow

interface AuthComponent : PredictiveBackComponent {

    val childStack: StateFlow<ChildStack<*, Child>>

    sealed interface Child {
        data class Welcome(val component: WelcomeComponent) : Child
        data class SignUp(val component: SignUpComponent) : Child
        data class SignIn(val component: SignInComponent) : Child
    }

    sealed interface Output {
        data object AuthSuccess : Output
    }
}