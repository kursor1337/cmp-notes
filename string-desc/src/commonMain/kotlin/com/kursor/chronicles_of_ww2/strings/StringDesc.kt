package com.kursor.chronicles_of_ww2.core.strings

import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

sealed interface StringDesc {
    class Resource(val res: StringResource) : StringDesc
    class Raw(val value: String) : StringDesc
}

@Composable
fun StringDesc.localized(): String {
    return when (this) {
        is StringDesc.Resource -> stringResource(this.res)
        is StringDesc.Raw -> this.value
    }
}

fun String.desc(): StringDesc {
    return StringDesc.Raw(this)
}