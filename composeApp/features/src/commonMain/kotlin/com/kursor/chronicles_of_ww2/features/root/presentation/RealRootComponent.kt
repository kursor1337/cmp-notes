package com.kursor.chronicles_of_ww2.features.root.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.childContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.replaceAll
import com.kursor.chronicles_of_ww2.core.ComponentFactory
import com.kursor.chronicles_of_ww2.core.message.createMessageComponent
import com.kursor.chronicles_of_ww2.core.network.tokens.TokenProvider
import com.kursor.chronicles_of_ww2.core.utils.toStateFlow
import com.kursor.chronicles_of_ww2.features.auth.createAuthComponent
import com.kursor.chronicles_of_ww2.features.auth.presentation.AuthComponent
import com.kursor.chronicles_of_ww2.features.notes.createNotesComponent
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.Serializable

class RealRootComponent(
    componentContext: ComponentContext,
    private val componentFactory: ComponentFactory,
    private val tokenProvider: TokenProvider
) : ComponentContext by componentContext, RootComponent {

    private val navigation = StackNavigation<ChildConfig>()

    override val childStack: StateFlow<ChildStack<*, RootComponent.Child>> = childStack(
        source = navigation,
        initialConfiguration = runBlocking {
            if (tokenProvider.getAuthTokens() != null) {
                ChildConfig.Notes
            } else {
                ChildConfig.Auth
            }
        },
        serializer = ChildConfig.serializer(),
        handleBackButton = true,
        childFactory = ::createChild
    ).toStateFlow(lifecycle)

    override val messageComponent = componentFactory.createMessageComponent(
        childContext(key = "message")
    )

    override fun onBack() = navigation.pop()

    private fun createChild(
        config: ChildConfig,
        componentContext: ComponentContext
    ): RootComponent.Child = when (config) {
        ChildConfig.Auth -> RootComponent.Child.Auth(
            componentFactory.createAuthComponent(
                componentContext,
                ::onAuthOutput
            )
        )
        ChildConfig.Notes -> RootComponent.Child.Notes(
            componentFactory.createNotesComponent(componentContext)
        )
    }

    private fun onAuthOutput(output: AuthComponent.Output) {
        when (output) {
            AuthComponent.Output.AuthSuccess -> {
                navigation.replaceAll(ChildConfig.Notes)
            }
        }
    }

    @Serializable
    private sealed interface ChildConfig {

        @Serializable
        data object Auth : ChildConfig

        @Serializable
        data object Notes : ChildConfig
    }
}