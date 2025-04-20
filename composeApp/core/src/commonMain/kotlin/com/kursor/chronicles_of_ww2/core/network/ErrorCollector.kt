package com.kursor.chronicles_of_ww2.core.network

import com.kursor.chronicles_of_ww2.core.error_handling.ApplicationException

fun interface ErrorCollector {
    fun collectNetworkError(exception: ApplicationException)
}