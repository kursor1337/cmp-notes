package com.kursor.chronicles_of_ww2.core.message.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.doOnCreate
import com.kursor.chronicles_of_ww2.core.message.data.MessageService
import com.kursor.chronicles_of_ww2.core.message.domain.Message
import com.kursor.chronicles_of_ww2.core.utils.componentScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class RealMessageComponent(
    componentContext: ComponentContext,
    private val messageService: MessageService,
) : ComponentContext by componentContext, MessageComponent {

    companion object {
        private const val SHOW_TIME = 4000L
    }

    override val visibleMessage = MutableStateFlow<Message?>(null)

    private var autoDismissJob: Job? = null

    init {
        lifecycle.doOnCreate(::collectMessages)
    }

    override fun onActionClick() {
        autoDismissJob?.cancel()
        visibleMessage.value?.action?.invoke()
        visibleMessage.value = null
    }

    private fun collectMessages() {
        componentScope.launch {
            messageService.messageFlow.collect { messageData ->
                showMessage(messageData)
            }
        }
    }

    private fun showMessage(message: Message) {
        autoDismissJob?.cancel()
        visibleMessage.value = message
        autoDismissJob = componentScope.launch {
            delay(SHOW_TIME)
            visibleMessage.value = null
        }
    }
}