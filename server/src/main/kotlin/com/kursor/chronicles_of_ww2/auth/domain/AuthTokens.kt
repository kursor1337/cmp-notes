package com.kursor.chronicles_of_ww2.auth.domain

@JvmInline
value class RefreshToken(val value: String)

@JvmInline
value class AccessToken(val value: String)

data class AuthTokens(
    val accessToken: AccessToken,
    val refreshToken: RefreshToken
)