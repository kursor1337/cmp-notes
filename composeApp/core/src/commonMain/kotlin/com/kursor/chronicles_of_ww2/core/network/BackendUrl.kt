package com.kursor.chronicles_of_ww2.core.network

import com.kursor.chronicles_of_ww2.core.configuration.Backend
import kotlin.jvm.JvmInline

@JvmInline
value class BackendUrl(val value: String) {

    companion object {
        private val MainDevelopmentUrl = BackendUrl("http://1052557-ct86910.tmweb.ru/api/v1/")
        private val MainProductionUrl = BackendUrl("http://1052557-ct86910.tmweb.ru/api/v1/")

        fun getMainUrl(backend: Backend) = when (backend) {
            Backend.Development -> MainDevelopmentUrl
            Backend.Production -> MainProductionUrl
        }
    }
}