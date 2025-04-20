package com.kursor.chronicles_of_ww2.dto.auth

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
class SignInRequest(
    @SerialName("login") val login: String,
    @SerialName("password") val password: String
)

@Serializable
class SignUpRequest(
    @SerialName("login") val login: String,
    @SerialName("password") val password: String
)

@Serializable
class RefreshTokensRequest(
    @SerialName("refreshToken") val refreshToken: String
)
