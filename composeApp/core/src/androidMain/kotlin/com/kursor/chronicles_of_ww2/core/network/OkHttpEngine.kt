package com.kursor.chronicles_of_ww2.core.network

import com.kursor.chronicles_of_ww2.core.debug_tools.AndroidDebugTools
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp

fun createOkHttpEngine(debugTools: AndroidDebugTools): HttpClientEngine {
    return OkHttp.create {
        debugTools.interceptors.forEach { addInterceptor(it) }
    }
}