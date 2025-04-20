package com.kursor.chronicles_of_ww2.features.auth.domain

interface AuthRepository {
    suspend fun signIn(
        login: String,
        password: String,
    ): SignInResult

    suspend fun signUp(
        login: String,
        password: String,
    ): SignUpResult
}

sealed interface SignInResult {
    data object Success : SignInResult
    data class Error(val error: String) : SignInResult
}

sealed interface SignUpResult {
    data object Success : SignUpResult
    data class Error(val error: String) : SignUpResult
}