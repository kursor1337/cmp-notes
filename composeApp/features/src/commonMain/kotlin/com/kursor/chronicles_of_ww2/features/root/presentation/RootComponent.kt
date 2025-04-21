package com.kursor.chronicles_of_ww2.features.root.presentation

import com.arkivanov.decompose.router.stack.ChildStack
import com.kursor.chronicles_of_ww2.core.message.presentation.MessageComponent
import com.kursor.chronicles_of_ww2.core.utils.PredictiveBackComponent
import com.kursor.chronicles_of_ww2.features.auth.presentation.AuthComponent
import com.kursor.chronicles_of_ww2.features.notes.presentation.NotesComponent
import kotlinx.coroutines.flow.StateFlow

/**
 * A root of a Decompose component tree.
 *
 * Note: Try to minimize child count in RootComponent. It should operate by flows of screens rather than separate screens.
 */
interface RootComponent : PredictiveBackComponent {

    val childStack: StateFlow<ChildStack<*, Child>>

    val messageComponent: MessageComponent

    sealed interface Child {
        data class Auth(val component: AuthComponent) : Child
        data class Notes(val component: NotesComponent) : Child
    }
}