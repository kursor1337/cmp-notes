package com.kursor.chronicles_of_ww2.core.message.domain

import com.kursor.chronicles_of_ww2.core.strings.StringDesc

data class Message(
    val text: StringDesc,
    val actionTitle: StringDesc? = null,
    val action: (() -> Unit)? = null
)