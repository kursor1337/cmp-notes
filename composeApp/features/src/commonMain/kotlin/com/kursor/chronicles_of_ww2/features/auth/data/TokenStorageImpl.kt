package com.kursor.chronicles_of_ww2.features.auth.data

import com.kursor.chronicles_of_ww2.core.network.tokens.AccessToken
import com.kursor.chronicles_of_ww2.core.network.tokens.AuthTokens
import com.kursor.chronicles_of_ww2.core.network.tokens.RefreshToken
import com.kursor.chronicles_of_ww2.core.settings.SettingsFactory
import com.kursor.chronicles_of_ww2.features.auth.domain.TokenStorage
import com.russhwolf.settings.ExperimentalSettingsApi

@OptIn(ExperimentalSettingsApi::class)
class TokenStorageImpl(
    settingsFactory: SettingsFactory
) : TokenStorage {

    companion object {
        private const val KEY_ACCESS_TOKEN = "key_access_token"
        private const val KEY_REFRESH_TOKEN = "key_refresh_token"
    }

    private val settings = settingsFactory.createEncryptedSettings()

    override suspend fun getAuthTokens(): AuthTokens? {
        val accessToken = settings.getStringOrNull(KEY_ACCESS_TOKEN) ?: return null
        val refreshToken = settings.getStringOrNull(KEY_REFRESH_TOKEN) ?: return null
        return AuthTokens(
            accessToken = AccessToken(accessToken),
            refreshToken = RefreshToken(refreshToken),
        )
    }

    override suspend fun saveTokens(authTokens: AuthTokens) {
        settings.putString(KEY_ACCESS_TOKEN, authTokens.accessToken.value)
        settings.putString(KEY_REFRESH_TOKEN, authTokens.refreshToken.value)
    }

    override suspend fun clearTokens() {
        settings.remove(KEY_ACCESS_TOKEN)
        settings.remove(KEY_REFRESH_TOKEN)
    }
}