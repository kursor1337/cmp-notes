package com.kursor.chronicles_of_ww2.core.message.data

import com.kursor.chronicles_of_ww2.core.message.domain.Message
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class MessageServiceImpl : MessageService {

    private val messageChannel = Channel<Message>(Channel.UNLIMITED)

    override val messageFlow = messageChannel.receiveAsFlow()

    override fun showMessage(message: Message) {
        messageChannel.trySend(message)
    }
}