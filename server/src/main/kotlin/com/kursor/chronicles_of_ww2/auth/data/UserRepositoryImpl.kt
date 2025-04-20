package com.kursor.chronicles_of_ww2.auth.data

import com.kursor.chronicles_of_ww2.auth.domain.AuthTokens
import com.kursor.chronicles_of_ww2.auth.domain.Login
import com.kursor.chronicles_of_ww2.auth.domain.Password
import com.kursor.chronicles_of_ww2.auth.domain.TokenService
import com.kursor.chronicles_of_ww2.auth.domain.UserRepository
import org.mindrot.jbcrypt.BCrypt

class UserRepositoryImpl(
    private val userService: UserService,
    private val tokenService: TokenService
) : UserRepository {

    override suspend fun loginUser(login: Login, password: Password): AuthTokens {
        val user = userService.read(login.value) ?: error("User not found")

        if (!BCrypt.checkpw(password.value, user.passwordHash)) {
            error("Wrong password")
        }

        return tokenService.generateAuthTokens(login)
    }

    override suspend fun registerUser(login: Login, password: Password): AuthTokens {
        if (userService.read(login.value) != null) error("User already exists")
        val passwordHash = BCrypt.hashpw(password.value, BCrypt.gensalt())
        val user = UserEntity(login.value, passwordHash)
        userService.create(user)
        return tokenService.generateAuthTokens(login)
    }
}