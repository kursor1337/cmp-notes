package com.kursor.chronicles_of_ww2.plugins

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.kursor.chronicles_of_ww2.auth.domain.TokenService
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.respond

fun Application.configureSecurity() {
    // Please read the jwt property from the config file if you are using EngineMain
    val jwtRealm = "ktor sample app"
    val jwtSecret = "secret"
    authentication {
        jwt {
            realm = jwtRealm
            verifier(
                JWT
                    .require(Algorithm.HMAC256(jwtSecret))
                    .build()
            )

            validate {
                val tokenType = it.payload.getClaim(TokenService.CLAIM_TOKEN_TYPE)?.asString()
                val login = it.payload.getClaim(TokenService.CLAIM_LOGIN)?.asString()
                if (tokenType == TokenService.TOKEN_TYPE_ACCESS && login != null) {
                    JWTPrincipal(it.payload)
                } else {
                    null
                }
            }

            challenge { m, d ->
                call.respond(HttpStatusCode.Unauthorized, "Token is not valid or has expired")
            }
        }
    }
}
