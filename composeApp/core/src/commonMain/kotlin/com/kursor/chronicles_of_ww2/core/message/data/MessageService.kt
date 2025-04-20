package com.kursor.chronicles_of_ww2.core.message.data

import com.kursor.chronicles_of_ww2.core.message.domain.Message
import kotlinx.coroutines.flow.Flow

/**
 * A service for centralized message showing
 */
interface MessageService {

    val messageFlow: Flow<Message>

    fun showMessage(message: Message)
}