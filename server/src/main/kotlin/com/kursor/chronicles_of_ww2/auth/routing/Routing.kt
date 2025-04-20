package com.kursor.chronicles_of_ww2.auth.routing

import com.kursor.chronicles_of_ww2.auth.domain.AuthTokens
import com.kursor.chronicles_of_ww2.auth.domain.Login
import com.kursor.chronicles_of_ww2.auth.domain.Password
import com.kursor.chronicles_of_ww2.auth.domain.RefreshToken
import com.kursor.chronicles_of_ww2.auth.domain.TokenService
import com.kursor.chronicles_of_ww2.auth.domain.UserRepository
import com.kursor.chronicles_of_ww2.dto.auth.RefreshTokensRequest
import com.kursor.chronicles_of_ww2.dto.auth.RefreshTokensResponse
import com.kursor.chronicles_of_ww2.dto.auth.SignInRequest
import com.kursor.chronicles_of_ww2.dto.auth.SignInResponse
import com.kursor.chronicles_of_ww2.dto.auth.SignUpRequest
import com.kursor.chronicles_of_ww2.dto.auth.SignUpResponse
import io.ktor.server.application.Application
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import io.ktor.server.routing.routing
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

fun Application.configureAuthRouting(
    userRepository: UserRepository,
    tokenService: TokenService
) {
    routing {
        route("api/v1/auth") {
            post("sign_in") {
                call.receive<SignInRequest>()
                    .let {
                        userRepository.loginUser(
                            login = Login(it.login),
                            password = Password(it.password)
                        )
                    }
                    .let {
                        call.respond(it.toSignInResponse())
                    }
            }

            post("sign_up") {
                call.receive<SignUpRequest>()
                    .let {
                        userRepository.registerUser(
                            login = Login(it.login),
                            password = Password(it.password)
                        )
                    }
                    .let {
                        call.respond(it.toSignUpResponse())
                    }
            }

            post("refresh") {
                call.receive<RefreshTokensRequest>()
                    .let { RefreshToken(it.refreshToken) }
                    .let { tokenService.refreshTokens(it) }
                    .let { call.respond(it.toRefreshTokensResponse()) }
            }
        }
    }
}

fun AuthTokens.toSignInResponse() = SignInResponse(
    accessToken = accessToken.value,
    refreshToken = refreshToken.value
)

fun AuthTokens.toSignUpResponse() = SignUpResponse(
    accessToken = accessToken.value,
    refreshToken = refreshToken.value
)

fun AuthTokens.toRefreshTokensResponse() = RefreshTokensResponse(
    accessToken = accessToken.value,
    refreshToken = refreshToken.value
)