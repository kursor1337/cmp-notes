package com.kursor.chronicles_of_ww2.core.network.tokens

interface TokenRefresher {
    suspend fun refreshTokens(): AuthTokens
}