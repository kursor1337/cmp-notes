package com.kursor.chronicles_of_ww2.core

import com.kursor.chronicles_of_ww2.auth.domain.Login
import com.kursor.chronicles_of_ww2.auth.domain.TokenService
import io.ktor.server.application.ApplicationCall
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.principal

val ApplicationCall.jwtTokenLogin: Login
    get() = principal<JWTPrincipal>()
        ?.payload
        ?.getClaim(TokenService.CLAIM_LOGIN)
        ?.asString()
        ?.let(::Login)
        ?: error("Access token is invalid")

fun ApplicationCall.longParameter(name: String) =
    parameters[name]
        ?.toLongOrNull()
        ?: error("Query parameter $name is missing")