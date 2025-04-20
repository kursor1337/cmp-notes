package com.kursor.chronicles_of_ww2.core.error_handling

import cmp_notes.composeapp.core.generated.resources.Res
import cmp_notes.composeapp.core.generated.resources.common_retry
import com.kursor.chronicles_of_ww2.core.strings.StringDesc
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * Allows to run a function safely (with error handing).
 */
fun safeRun(
    errorHandler: ErrorHandler,
    showError: Boolean = true,
    onErrorHandled: ((e: Exception) -> Unit)? = null,
    block: () -> Unit
) {
    try {
        block()
    } catch (e: Exception) {
        errorHandler.handleError(e, showError)
        onErrorHandled?.invoke(e)
    }
}

/**
 * Allows to run a suspend function safely (with error handing).
 */
fun CoroutineScope.safeLaunch(
    errorHandler: ErrorHandler,
    showError: Boolean = true,
    onErrorHandled: ((e: Exception) -> Unit)? = null,
    block: suspend () -> Unit
): Job {
    return launch {
        try {
            block()
        } catch (e: CancellationException) {
            // do nothing
        } catch (e: Exception) {
            errorHandler.handleError(e, showError)
            onErrorHandled?.invoke(e)
        }
    }
}

/**
 * Allows to run a suspend function safely (with error handing) and allows to retry a failed action.
 */
fun CoroutineScope.safeLaunchRetryable(
    errorHandler: ErrorHandler,
    onErrorHandled: ((e: Exception) -> Unit)? = null,
    retryActionTitle: StringDesc = StringDesc.Resource(Res.string.common_retry),
    retryAction: () -> Unit,
    block: suspend () -> Unit
): Job {
    return launch {
        try {
            block()
        } catch (e: CancellationException) {
            // do nothing
        } catch (e: Exception) {
            errorHandler.handleErrorRetryable(
                exception = e,
                retryActionTitle = retryActionTitle,
                retryAction = retryAction
            )
            onErrorHandled?.invoke(e)
        }
    }
}