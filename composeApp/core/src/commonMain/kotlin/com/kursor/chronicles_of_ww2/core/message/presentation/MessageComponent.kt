package com.kursor.chronicles_of_ww2.core.message.presentation

import com.kursor.chronicles_of_ww2.core.message.domain.Message
import kotlinx.coroutines.flow.StateFlow

/**
 * A component for centralized message showing. There should be only one instance
 * of this component in the app connected to the root component.
 */
interface MessageComponent {

    val visibleMessage: StateFlow<Message?>

    fun onActionClick()
}