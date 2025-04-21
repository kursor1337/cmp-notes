package com.kursor.chronicles_of_ww2.core.network

import com.arkivanov.decompose.DefaultComponentContext
import com.kursor.chronicles_of_ww2.core.network.tokens.AuthInterceptor
import com.kursor.chronicles_of_ww2.core.network.tokens.TokenProvider
import com.kursor.chronicles_of_ww2.core.network.tokens.TokenRefresher
import de.jensklingenberg.ktorfit.Ktorfit
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.HttpSend
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.plugin
import io.ktor.serialization.kotlinx.json.json

/**
 * Creates implementations of Ktorfit APIs.
 */
class NetworkApiFactory(
    private val loggingEnabled: Boolean,
    private val backendUrl: BackendUrl,
    private val httpClientEngine: HttpClientEngine,
    private val tokenProvider: TokenProvider,
    private val tokenRefresher: Lazy<TokenRefresher>
) {
    companion object {
        private const val CONNECT_TIMEOUT_MILLISECONDS = 30000L
        private const val READ_WRITE_TIMEOUT_MILLISECONDS = 60000L
    }

    private val json = createDefaultJson()
    private val authorizedHttpClient by lazy { createHttpClient(authorized = true) }
    private val unauthorizedHttpClient by lazy { createHttpClient(authorized = false) }

    /**
     * Ktorfit for creating of authorized APIs
     */
    val authorizedKtorfit by lazy { createKtorfit(authorizedHttpClient) }

    /**
     * Ktorfit for creating of APIs that don't require authorization
     */
    val unauthorizedKtorfit by lazy { createKtorfit(unauthorizedHttpClient) }

    private fun createHttpClient(authorized: Boolean): HttpClient {
        return HttpClient(httpClientEngine) {
            install(Logging) {
                logger = createKtorLogger()
                level = if (loggingEnabled) LogLevel.ALL else LogLevel.NONE
            }

            install(ContentNegotiation) {
                json(json)
            }

            install(HttpTimeout) {
                connectTimeoutMillis = CONNECT_TIMEOUT_MILLISECONDS
                requestTimeoutMillis = READ_WRITE_TIMEOUT_MILLISECONDS
            }

            defaultRequest {
                url(backendUrl.value)
                headers.append("Content-Type", "application/json")
            }

            setupErrorConverter()
        }.apply {
            if (authorized) {
                val authInterceptor = AuthInterceptor(tokenProvider, tokenRefresher.value)
                plugin(HttpSend).intercept { request ->
                    authInterceptor.intercept(this, request)
                }
            }
        }
    }

    private fun createKtorfit(httpClient: HttpClient): Ktorfit {
        return Ktorfit.Builder()
            .baseUrl(backendUrl.value)
            .httpClient(httpClient)
            .build()
    }
}