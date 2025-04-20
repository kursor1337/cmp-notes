package com.kursor.chronicles_of_ww2.features.auth.presentation.sign_up

import ru.mobileup.kmm_form_validation.control.InputControl

interface SignUpComponent {

    val loginInputControl: InputControl
    val passwordInputControl: InputControl

    fun onSignUpClick()

    fun onBack()

    sealed interface Output {
        data object SignUpSuccess : Output
        data object BackRequested : Output
    }
}