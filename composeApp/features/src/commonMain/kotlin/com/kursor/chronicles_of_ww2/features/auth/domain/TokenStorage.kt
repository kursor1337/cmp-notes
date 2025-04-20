package com.kursor.chronicles_of_ww2.features.auth.domain

import com.kursor.chronicles_of_ww2.core.network.tokens.AuthTokens
import com.kursor.chronicles_of_ww2.core.network.tokens.TokenProvider

interface TokenStorage : TokenProvider {
    suspend fun saveTokens(authTokens: AuthTokens)
    suspend fun clearTokens()
}