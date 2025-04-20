package com.kursor.chronicles_of_ww2.core.message.presentation

import com.kursor.chronicles_of_ww2.core.message.domain.Message
import com.kursor.chronicles_of_ww2.core.strings.StringDesc
import kotlinx.coroutines.flow.MutableStateFlow

class FakeMessageComponent : MessageComponent {

    override val visibleMessage = MutableStateFlow(Message(StringDesc.Raw("Message")))

    override fun onActionClick() = Unit
}