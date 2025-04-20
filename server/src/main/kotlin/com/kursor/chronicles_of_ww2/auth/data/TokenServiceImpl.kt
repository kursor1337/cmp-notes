package com.kursor.chronicles_of_ww2.auth.data

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.kursor.chronicles_of_ww2.auth.domain.AccessToken
import com.kursor.chronicles_of_ww2.auth.domain.AuthTokens
import com.kursor.chronicles_of_ww2.auth.domain.Login
import com.kursor.chronicles_of_ww2.auth.domain.RefreshToken
import com.kursor.chronicles_of_ww2.auth.domain.TokenService
import com.kursor.chronicles_of_ww2.core.JwtSecret
import org.h2.command.Token
import java.util.Date
import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.minutes

class TokenServiceImpl(private val secret: JwtSecret) : TokenService {

    companion object {
        private val REFRESH_TOKEN_LIFETIME = 30.days.inWholeMilliseconds
        private val ACCESS_TOKEN_LIFETIME = 2.minutes.inWholeMilliseconds
    }

    private fun getAccessTokenExpirationDate() =
        Date(System.currentTimeMillis() + ACCESS_TOKEN_LIFETIME)

    private fun getRefreshTokenExpirationDate() =
        Date(System.currentTimeMillis() + REFRESH_TOKEN_LIFETIME)

    override fun generateAuthTokens(login: Login): AuthTokens {
        val accessToken = JWT.create()
            .withClaim(TokenService.CLAIM_LOGIN, login.value)
            .withClaim(TokenService.CLAIM_TOKEN_TYPE, TokenService.TOKEN_TYPE_ACCESS)
            .withExpiresAt(getAccessTokenExpirationDate())
            .sign(Algorithm.HMAC256(secret.value))

        val refreshToken = JWT.create()
            .withClaim(TokenService.CLAIM_LOGIN, login.value)
            .withClaim(TokenService.CLAIM_TOKEN_TYPE, TokenService.TOKEN_TYPE_REFRESH)
            .withExpiresAt(getRefreshTokenExpirationDate())
            .sign(Algorithm.HMAC256(secret.value))

        return AuthTokens(
            AccessToken(accessToken),
            RefreshToken(refreshToken)
        )
    }

    override fun refreshTokens(refreshToken: RefreshToken): AuthTokens {
        val decodedToken = JWT.decode(refreshToken.value)

        if (decodedToken.expiresAt < Date(System.currentTimeMillis())) {
            error("Refresh token is expired")
        }

        if (decodedToken.getClaim(TokenService.CLAIM_TOKEN_TYPE).asString() != TokenService.TOKEN_TYPE_REFRESH) {
            error("Not a refresh token")
        }

        JWT
            .require(Algorithm.HMAC256(secret.value))
            .build()
            .verify(refreshToken.value)

        val accessToken = JWT.create()
            .withClaim(
                TokenService.CLAIM_LOGIN,
                decodedToken.getClaim(TokenService.CLAIM_LOGIN).asString()
            )
            .withClaim(
                TokenService.CLAIM_TOKEN_TYPE,
                TokenService.TOKEN_TYPE_ACCESS
            )
            .withExpiresAt(getAccessTokenExpirationDate())
            .sign(Algorithm.HMAC256(secret.value))

        val newRefreshToken = if (decodedToken.expiresAt.time - System.currentTimeMillis() < REFRESH_TOKEN_LIFETIME * 2 / 3) {
            JWT.create()
                .withClaim(
                    TokenService.CLAIM_LOGIN,
                    decodedToken.getClaim(TokenService.CLAIM_LOGIN).asString()
                )
                .withClaim(
                    TokenService.CLAIM_TOKEN_TYPE,
                    TokenService.TOKEN_TYPE_REFRESH
                ).withExpiresAt(getRefreshTokenExpirationDate())
                .sign(Algorithm.HMAC256(secret.value))
        } else {
            refreshToken.value
        }

        return AuthTokens(
            AccessToken(accessToken),
            RefreshToken(newRefreshToken)
        )
    }
}