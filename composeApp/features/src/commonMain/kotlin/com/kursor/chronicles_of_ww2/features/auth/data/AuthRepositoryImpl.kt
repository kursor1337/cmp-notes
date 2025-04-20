package com.kursor.chronicles_of_ww2.features.auth.data

import com.kursor.chronicles_of_ww2.core.network.tokens.AccessToken
import com.kursor.chronicles_of_ww2.core.network.tokens.AuthTokens
import com.kursor.chronicles_of_ww2.core.network.tokens.RefreshToken
import com.kursor.chronicles_of_ww2.core.network.tokens.TokenProvider
import com.kursor.chronicles_of_ww2.core.network.tokens.TokenRefresher
import com.kursor.chronicles_of_ww2.dto.auth.RefreshTokensRequest
import com.kursor.chronicles_of_ww2.dto.auth.SignInRequest
import com.kursor.chronicles_of_ww2.dto.auth.SignUpRequest
import com.kursor.chronicles_of_ww2.features.auth.domain.AuthRepository
import com.kursor.chronicles_of_ww2.features.auth.domain.SignInResult
import com.kursor.chronicles_of_ww2.features.auth.domain.SignUpResult
import com.kursor.chronicles_of_ww2.features.auth.domain.TokenStorage

class AuthRepositoryImpl(
    private val authApi: AuthApi,
    private val tokenStorage: TokenStorage
) : AuthRepository, TokenRefresher {

    override suspend fun signIn(login: String, password: String): SignInResult {
        return try {
            val tokens = authApi.signIn(SignInRequest(login, password))
            tokenStorage.saveTokens(
                AuthTokens(
                    accessToken = AccessToken(tokens.accessToken),
                    refreshToken = RefreshToken(tokens.refreshToken)
                )
            )
            SignInResult.Success
        } catch (e: Exception) {
            SignInResult.Error(e.message ?: "Unknown error")
        }

    }

    override suspend fun signUp(login: String, password: String): SignUpResult {
        return try {
            val tokens = authApi.signUp(SignUpRequest(login, password))
            tokenStorage.saveTokens(
                AuthTokens(
                    accessToken = AccessToken(tokens.accessToken),
                    refreshToken = RefreshToken(tokens.refreshToken)
                )
            )
            SignUpResult.Success
        } catch (e: Exception) {
            SignUpResult.Error(e.message ?: "Unknown error")
        }
    }

    override suspend fun refreshTokens(): AuthTokens {
        val refreshToken = tokenStorage
            .getAuthTokens()
            ?.refreshToken
            ?.value
            ?: throw IllegalStateException("Refresh token is null")
        return authApi
            .refreshToken(
                RefreshTokensRequest(refreshToken)
            )
            .let {
                AuthTokens(
                    accessToken = AccessToken(it.accessToken),
                    refreshToken = RefreshToken(it.refreshToken)
                )
            }
    }
}