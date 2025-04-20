package com.kursor.chronicles_of_ww2.features.auth.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.pushNew
import com.kursor.chronicles_of_ww2.core.ComponentFactory
import com.kursor.chronicles_of_ww2.core.utils.toStateFlow
import com.kursor.chronicles_of_ww2.features.auth.createSignInComponent
import com.kursor.chronicles_of_ww2.features.auth.createSignUpComponent
import com.kursor.chronicles_of_ww2.features.auth.createWelcomeComponent
import com.kursor.chronicles_of_ww2.features.auth.presentation.sign_in.SignInComponent
import com.kursor.chronicles_of_ww2.features.auth.presentation.sign_up.SignUpComponent
import com.kursor.chronicles_of_ww2.features.auth.presentation.welcome.WelcomeComponent
import kotlinx.coroutines.flow.StateFlow
import kotlinx.serialization.Serializable

class RealAuthComponent(
    componentContext: ComponentContext,
    private val onOutput: (AuthComponent.Output) -> Unit,
    private val componentFactory: ComponentFactory
) : ComponentContext by componentContext, AuthComponent {

    private val navigation = StackNavigation<ChildConfig>()

    override val childStack: StateFlow<ChildStack<*, AuthComponent.Child>> = childStack(
        source = navigation,
        serializer = ChildConfig.serializer(),
        initialConfiguration = ChildConfig.Welcome,
        handleBackButton = true,
        childFactory = ::createChild
    ).toStateFlow(lifecycle)

    override fun onBack() = navigation.pop()

    private fun createChild(
        childConfig: ChildConfig,
        componentContext: ComponentContext
    ) = when (childConfig) {
        ChildConfig.Welcome -> AuthComponent.Child.Welcome(
            component = componentFactory.createWelcomeComponent(
                componentContext,
                ::onWelcomeOutput
            )
        )

        ChildConfig.SignUp -> AuthComponent.Child.SignUp(
            component = componentFactory.createSignUpComponent(
                componentContext,
                ::onSignUpOutput
            )
        )

        ChildConfig.SignIn -> AuthComponent.Child.SignIn(
            component = componentFactory.createSignInComponent(
                componentContext,
                ::onSignInOutput
            )
        )
    }

    private fun onWelcomeOutput(output: WelcomeComponent.Output) {
        when (output) {
            WelcomeComponent.Output.SignInRequested -> {
                navigation.pushNew(ChildConfig.SignIn)
            }
            WelcomeComponent.Output.SignUpRequested -> {
                navigation.pushNew(ChildConfig.SignUp)
            }
        }
    }

    private fun onSignUpOutput(output: SignUpComponent.Output) {
        when (output) {
            SignUpComponent.Output.SignUpSuccess -> {
                onOutput(AuthComponent.Output.AuthSuccess)
            }

            SignUpComponent.Output.BackRequested -> navigation.pop()
        }
    }

    private fun onSignInOutput(output: SignInComponent.Output) {
        when (output) {
            SignInComponent.Output.SignInSuccess -> {
                onOutput(AuthComponent.Output.AuthSuccess)
            }

            SignInComponent.Output.BackRequested -> {
                println("AuthComponent onBack")
                navigation.pop()
            }
        }
    }

    @Serializable
    sealed interface ChildConfig {

        @Serializable
        data object Welcome : ChildConfig

        @Serializable
        data object SignUp : ChildConfig

        @Serializable
        data object SignIn : ChildConfig
    }
}