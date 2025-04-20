package com.kursor.chronicles_of_ww2.features.auth.presentation.sign_in

import ru.mobileup.kmm_form_validation.control.InputControl

interface SignInComponent {

    val loginInputControl: InputControl
    val passwordInputControl: InputControl

    fun onSignUpClick()

    fun onBack()

    sealed interface Output {
        data object SignInSuccess : Output
        data object BackRequested : Output
    }
}