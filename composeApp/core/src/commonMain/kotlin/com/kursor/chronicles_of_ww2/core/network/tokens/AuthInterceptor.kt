package com.kursor.chronicles_of_ww2.core.network.tokens

import io.ktor.client.call.HttpClientCall
import io.ktor.client.plugins.Sender
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class AuthInterceptor(
    private val tokenProvider: TokenProvider,
    private val tokenRefresher: TokenRefresher
) {
    companion object {
        private const val ACCESS_TOKEN_PARAMETER_NAME = "accessToken"
    }

    private val mutex = Mutex()

    suspend fun intercept(sender: Sender, request: HttpRequestBuilder): HttpClientCall {
        val accessToken = tokenProvider.getAuthTokens()?.accessToken
        if (accessToken != null) request.attachAccessToken(accessToken.value)
        val call = sender.execute(request)

        return if (accessToken != null && call.response.status == HttpStatusCode.Unauthorized) {
            val refreshedAccessToken = refreshTokens(accessToken).accessToken
            request.attachAccessToken(refreshedAccessToken.value)
            sender.execute(request)
        } else {
            call
        }
    }

    private suspend fun refreshTokens(accessTokenUsedForFailedRequest: AccessToken): AuthTokens {
        return mutex.withLock { // use mutex to prevent multiple refreshes simultaneously
            val currentTokens = tokenProvider.getAuthTokens()
            if (currentTokens != null && accessTokenUsedForFailedRequest != currentTokens.accessToken) {
                currentTokens // already refreshed by other request
            } else {
                tokenRefresher.refreshTokens()
            }
        }
    }

    private fun HttpRequestBuilder.attachAccessToken(accessToken: String) {
        headers["Authorization"] = "Bearer $accessToken"
    }
}