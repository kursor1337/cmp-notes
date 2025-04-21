package com.kursor.chronicles_of_ww2.features.auth.presentation.sign_up

import com.arkivanov.decompose.ComponentContext
import com.kursor.chronicles_of_ww2.core.error_handling.ErrorHandler
import com.kursor.chronicles_of_ww2.core.error_handling.safeLaunch
import com.kursor.chronicles_of_ww2.core.strings.desc
import com.kursor.chronicles_of_ww2.core.utils.InputControl
import com.kursor.chronicles_of_ww2.core.utils.componentScope
import com.kursor.chronicles_of_ww2.features.auth.domain.AuthRepository
import com.kursor.chronicles_of_ww2.features.auth.domain.SignUpResult
import ru.mobileup.kmm_form_validation.control.InputControl
import ru.mobileup.kmm_form_validation.options.PasswordVisualTransformation

class RealSignUpComponent(
    componentContext: ComponentContext,
    private val onOutput: (SignUpComponent.Output) -> Unit,
    private val authRepository: AuthRepository,
    private val errorHandler: ErrorHandler
) : ComponentContext by componentContext, SignUpComponent {

    override val loginInputControl: InputControl = InputControl()

    override val passwordInputControl: InputControl = InputControl(
        visualTransformation = PasswordVisualTransformation()
    )

    override fun onSignUpClick() {
        componentScope.safeLaunch(errorHandler) {
            val login = loginInputControl.text.value
            val password = passwordInputControl.text.value

            when (val result = authRepository.signUp(login, password)) {
                is SignUpResult.Error -> {
                    passwordInputControl.error.value = result.error.desc()
                }
                SignUpResult.Success -> {
                    onOutput(SignUpComponent.Output.SignUpSuccess)
                }
            }
        }
    }

    override fun onBack() {
        onOutput(SignUpComponent.Output.BackRequested)
    }
}