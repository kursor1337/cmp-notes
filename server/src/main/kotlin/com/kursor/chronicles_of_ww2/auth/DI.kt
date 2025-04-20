package com.kursor.chronicles_of_ww2.auth

import com.kursor.chronicles_of_ww2.auth.data.TokenServiceImpl
import com.kursor.chronicles_of_ww2.auth.data.UserRepositoryImpl
import com.kursor.chronicles_of_ww2.auth.data.UserService
import com.kursor.chronicles_of_ww2.auth.domain.TokenService
import com.kursor.chronicles_of_ww2.auth.domain.UserRepository
import com.kursor.chronicles_of_ww2.core.JwtSecret
import org.koin.dsl.module

val authModule = module {
    single<TokenService> { TokenServiceImpl(get()) }
    single<UserService> { UserService(get()) }
    single<UserRepository> { UserRepositoryImpl(get(), get()) }
}