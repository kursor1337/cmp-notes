package com.kursor.chronicles_of_ww2.auth.domain

interface UserRepository {

    suspend fun loginUser(
        login: Login,
        password: Password
    ): AuthTokens

    suspend fun registerUser(
        login: Login,
        password: Password
    ): AuthTokens
}