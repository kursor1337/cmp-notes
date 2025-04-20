package com.kursor.chronicles_of_ww2.core.network.tokens

interface TokenProvider {
    suspend fun getAuthTokens(): AuthTokens?
}