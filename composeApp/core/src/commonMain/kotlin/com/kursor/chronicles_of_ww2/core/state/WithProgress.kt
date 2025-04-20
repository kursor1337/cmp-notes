package com.kursor.chronicles_of_ww2.core.state

import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.reflect.KMutableProperty0

suspend fun withProgress(
    progressProperty: KMutableProperty0<Boolean>,
    block: suspend () -> Unit
) {
    try {
        progressProperty.set(true)
        block()
    } finally {
        progressProperty.set(false)
    }
}

suspend fun withProgress(
    progressFlow: MutableStateFlow<Boolean>,
    block: suspend () -> Unit
) {
    try {
        progressFlow.value = true
        block()
    } finally {
        progressFlow.value = false
    }
}