package com.kursor.chronicles_of_ww2.auth.domain

interface TokenService {
    fun generateAuthTokens(login: Login): AuthTokens
    fun refreshTokens(refreshToken: RefreshToken): AuthTokens

    companion object {
        const val CLAIM_LOGIN = "login"
        const val CLAIM_TOKEN_TYPE = "token_type"

        const val TOKEN_TYPE_ACCESS = "access"
        const val TOKEN_TYPE_REFRESH = "refresh"
    }
}