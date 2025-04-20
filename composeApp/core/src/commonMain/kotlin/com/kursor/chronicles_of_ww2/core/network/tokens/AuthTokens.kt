package com.kursor.chronicles_of_ww2.core.network.tokens

import kotlin.jvm.JvmInline

@JvmInline
value class AccessToken(val value: String)

@JvmInline
value class RefreshToken(val value: String)

data class AuthTokens(
    val accessToken: AccessToken,
    val refreshToken: RefreshToken
)
