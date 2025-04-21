package com.kursor.chronicles_of_ww2.features.auth

import com.arkivanov.decompose.ComponentContext
import com.kursor.chronicles_of_ww2.core.ComponentFactory
import com.kursor.chronicles_of_ww2.core.network.NetworkApiFactory
import com.kursor.chronicles_of_ww2.core.network.tokens.TokenProvider
import com.kursor.chronicles_of_ww2.core.network.tokens.TokenRefresher
import com.kursor.chronicles_of_ww2.features.auth.data.AuthApi
import com.kursor.chronicles_of_ww2.features.auth.data.AuthRepositoryImpl
import com.kursor.chronicles_of_ww2.features.auth.data.TokenStorageImpl
import com.kursor.chronicles_of_ww2.features.auth.data.createAuthApi
import com.kursor.chronicles_of_ww2.features.auth.domain.AuthRepository
import com.kursor.chronicles_of_ww2.features.auth.domain.TokenStorage
import com.kursor.chronicles_of_ww2.features.auth.presentation.AuthComponent
import com.kursor.chronicles_of_ww2.features.auth.presentation.RealAuthComponent
import com.kursor.chronicles_of_ww2.features.auth.presentation.sign_in.RealSignInComponent
import com.kursor.chronicles_of_ww2.features.auth.presentation.sign_in.SignInComponent
import com.kursor.chronicles_of_ww2.features.auth.presentation.sign_up.RealSignUpComponent
import com.kursor.chronicles_of_ww2.features.auth.presentation.sign_up.SignUpComponent
import com.kursor.chronicles_of_ww2.features.auth.presentation.welcome.RealWelcomeComponent
import com.kursor.chronicles_of_ww2.features.auth.presentation.welcome.WelcomeComponent
import org.koin.core.component.get
import org.koin.dsl.binds
import org.koin.dsl.module

val authModule = module {
    single<AuthApi> { get<NetworkApiFactory>().unauthorizedKtorfit.createAuthApi() }
    single { TokenStorageImpl(get()) } binds arrayOf(
        TokenProvider::class,
        TokenStorage::class
    )
    single { AuthRepositoryImpl(get(), get()) } binds arrayOf(
        AuthRepository::class,
        TokenRefresher::class
    )
}

fun ComponentFactory.createAuthComponent(
    componentContext: ComponentContext,
    onOutput: (AuthComponent.Output) -> Unit
): AuthComponent {
    return RealAuthComponent(
        componentContext,
        onOutput,
        get()
    )
}

fun ComponentFactory.createWelcomeComponent(
    componentContext: ComponentContext,
    onOutput: (WelcomeComponent.Output) -> Unit
): WelcomeComponent {
    return RealWelcomeComponent(
        componentContext,
        onOutput
    )
}

fun ComponentFactory.createSignUpComponent(
    componentContext: ComponentContext,
    onOutput: (SignUpComponent.Output) -> Unit
): SignUpComponent {
    return RealSignUpComponent(
        componentContext,
        onOutput,
        get(),
        get()
    )
}

fun ComponentFactory.createSignInComponent(
    componentContext: ComponentContext,
    onOutput: (SignInComponent.Output) -> Unit
): SignInComponent {
    return RealSignInComponent(
        componentContext,
        onOutput,
        get(),
        get()
    )
}
