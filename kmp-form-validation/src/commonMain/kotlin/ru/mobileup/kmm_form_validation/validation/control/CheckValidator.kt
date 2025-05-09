package ru.mobileup.kmm_form_validation.validation.control

import com.kursor.chronicles_of_ww2.core.strings.StringDesc
import ru.mobileup.kmm_form_validation.control.CheckControl
import ru.mobileup.kmm_form_validation.validation.form.FormValidatorBuilder
import ru.mobileup.kmm_form_validation.validation.form.checked

/**
 * Validator for [CheckControl].
 * @param validation implements validation logic.
 * @param showError a callback that is called to show one-time error such as a toast. For permanent errors use [CheckControl.error] state.
 *
 * Use [FormValidatorBuilder.checked] to create it with a handy DSL.
 */
class CheckValidator constructor(
    override val control: ru.mobileup.kmm_form_validation.control.CheckControl,
    private val validation: (Boolean) -> ValidationResult,
    private val showError: ((StringDesc) -> Unit)? = null
) : ControlValidator<ru.mobileup.kmm_form_validation.control.CheckControl> {

    override fun validate(displayResult: Boolean): ValidationResult {
        return getValidationResult().also {
            if (displayResult) displayValidationResult(it)
        }
    }

    private fun getValidationResult(): ValidationResult {
        if (control.skipInValidation.value) {
            return ValidationResult.Skipped
        }

        return validation(control.value.value)
    }

    private fun displayValidationResult(validationResult: ValidationResult) =
        when (validationResult) {
            ValidationResult.Valid, ValidationResult.Skipped -> control.error.value = null
            is ValidationResult.Invalid -> {
                control.error.value = validationResult.errorMessage
                showError?.invoke(validationResult.errorMessage)
            }
        }
}