package com.kursor.chronicles_of_ww2.features.auth.presentation.sign_in

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import ru.mobileup.kmm_form_validation.control.InputControl

@OptIn(DelicateCoroutinesApi::class)
class FakeSignInComponent : SignInComponent {
    override val loginInputControl: InputControl = InputControl(GlobalScope)
    override val passwordInputControl: InputControl = InputControl(GlobalScope)

    override fun onSignUpClick() = Unit
    override fun onBack() = Unit
}