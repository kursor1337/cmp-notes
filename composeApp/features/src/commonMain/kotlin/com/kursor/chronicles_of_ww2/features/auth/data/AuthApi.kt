package com.kursor.chronicles_of_ww2.features.auth.data

import com.kursor.chronicles_of_ww2.dto.auth.RefreshTokensRequest
import com.kursor.chronicles_of_ww2.dto.auth.RefreshTokensResponse
import com.kursor.chronicles_of_ww2.dto.auth.SignInRequest
import com.kursor.chronicles_of_ww2.dto.auth.SignInResponse
import com.kursor.chronicles_of_ww2.dto.auth.SignUpRequest
import com.kursor.chronicles_of_ww2.dto.auth.SignUpResponse
import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.POST

interface AuthApi {

    @POST("auth/sign_in")
    suspend fun signIn(
        @Body request: SignInRequest
    ): SignInResponse

    @POST("auth/sign_up")
    suspend fun signUp(
        @Body request: SignUpRequest
    ): SignUpResponse

    @POST("auth/refresh")
    suspend fun refreshToken(
        @Body request: RefreshTokensRequest
    ): RefreshTokensResponse
}