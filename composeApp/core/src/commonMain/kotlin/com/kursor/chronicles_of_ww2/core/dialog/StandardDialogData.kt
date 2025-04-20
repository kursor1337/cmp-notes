package com.kursor.chronicles_of_ww2.core.dialog

import com.kursor.chronicles_of_ww2.core.strings.StringDesc
import com.kursor.chronicles_of_ww2.core.strings.desc

data class StandardDialogData(
    val title: StringDesc,
    val message: StringDesc? = null,
    val confirmButton: DialogButton,
    val dismissButton: DialogButton? = null,
    val dismissableByUser: Boolean = true
) {

    companion object {
        val MOCK = StandardDialogData(
            title = "Title".desc(),
            message = "Message".desc(),
            confirmButton = DialogButton(
                text = "Next".desc(),
                action = {}
            ),
            dismissButton = DialogButton(
                text = "Cancel".desc(),
                action = {}
            )
        )
    }
}

data class DialogButton(
    val text: StringDesc,
    val action: () -> Unit
)