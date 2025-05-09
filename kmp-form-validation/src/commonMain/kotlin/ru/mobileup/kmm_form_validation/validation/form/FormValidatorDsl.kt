package ru.mobileup.kmm_form_validation.validation.form

import com.kursor.chronicles_of_ww2.core.strings.StringDesc
import kotlinx.coroutines.CoroutineScope
import org.jetbrains.compose.resources.StringResource
import ru.mobileup.kmm_form_validation.control.CheckControl
import ru.mobileup.kmm_form_validation.control.InputControl
import ru.mobileup.kmm_form_validation.control.ValidatableControl
import ru.mobileup.kmm_form_validation.validation.control.CheckValidator
import ru.mobileup.kmm_form_validation.validation.control.ControlValidator
import ru.mobileup.kmm_form_validation.validation.control.InputValidatorBuilder
import ru.mobileup.kmm_form_validation.validation.control.ValidationResult

class FormValidatorBuilder {

    private val validators = mutableMapOf<ValidatableControl<*>, ControlValidator<*>>()

    /**
     * Allows to add additional features to form validation. @see [FormValidationFeature].
     */
    var features = listOf<FormValidationFeature>()

    /**
     * Adds arbitrary [ControlValidator].
     */
    fun validator(validator: ControlValidator<*>) {
        val control = validator.control
        if (validators.containsKey(control)) {
            throw IllegalArgumentException("Validator for $control is already added.")
        }
        validators[control] = validator
    }

    /**
     * Adds a validator for [CheckControl].
     * @param validation implements validation logic.
     * @param showError a callback that is called to show one-time error such as a toast. For permanent errors use [CheckControl.error] state.
     */
    fun check(
        checkControl: ru.mobileup.kmm_form_validation.control.CheckControl,
        validation: (Boolean) -> ValidationResult,
        showError: ((StringDesc) -> Unit)? = null
    ) {
        val checkValidator = CheckValidator(checkControl, validation, showError)
        validator(checkValidator)
    }

    /**
     * Adds a validator for [InputControl]. Use [buildBlock] to configure validation for a given control.
     * @param required specifies if blank input is considered valid.
     */
    fun input(
        inputControl: ru.mobileup.kmm_form_validation.control.InputControl,
        required: Boolean = true,
        buildBlock: InputValidatorBuilder.() -> Unit
    ) {
        val inputValidator = InputValidatorBuilder(inputControl, required)
            .apply(buildBlock)
            .build()
        validator(inputValidator)
    }

    fun build(coroutineScope: CoroutineScope): FormValidator {
        return FormValidator(validators).apply {
            features.forEach { feature ->
                feature.install(coroutineScope, this)
            }
        }
    }
}

/**
 * Creates [FormValidator]. Use [buildBlock] to configure validation.
 */
fun CoroutineScope.formValidator(buildBlock: FormValidatorBuilder.() -> Unit): FormValidator {
    return FormValidatorBuilder()
        .apply(buildBlock)
        .build(this)
}

/**
 * Adds a validator that checks that [checkControl] is checked.
 */
fun FormValidatorBuilder.checked(
    checkControl: ru.mobileup.kmm_form_validation.control.CheckControl,
    errorMessage: StringDesc,
    showError: ((StringDesc) -> Unit)? = null
) {
    this.check(
        checkControl,
        validation = {
            if (it) ValidationResult.Valid else ValidationResult.Invalid(errorMessage)
        },
        showError
    )
}

/**
 * Adds a validator that checks that [checkControl] is checked.
 */
fun FormValidatorBuilder.checked(
    checkControl: ru.mobileup.kmm_form_validation.control.CheckControl,
    errorMessageRes: StringResource,
    showError: ((StringDesc) -> Unit)? = null
) {
    checked(checkControl, StringDesc.Resource(errorMessageRes), showError)
}