package ru.mobileup.kmm_form_validation.validation.control

import com.kursor.chronicles_of_ww2.core.strings.StringDesc
import org.jetbrains.compose.resources.StringResource

class InputValidatorBuilder(
    private val inputControl: ru.mobileup.kmm_form_validation.control.InputControl,
    private val required: Boolean
) {

    private val validations = mutableListOf<(String) -> ValidationResult>()

    /**
     * Adds an arbitrary validation. Validations are processed sequentially until first error.
     */
    fun validation(validation: (String) -> ValidationResult) {
        validations.add(validation)
    }

    fun build(): InputValidator {
        return InputValidator(inputControl, required, validations)
    }
}

/**
 * Adds an arbitrary validation. Validations are processed sequentially until first error.
 */
fun InputValidatorBuilder.validation(isValid: (String) -> Boolean, errorMessage: StringDesc) {
    validation {
        if (isValid(it)) {
            ValidationResult.Valid
        } else {
            ValidationResult.Invalid(errorMessage)
        }
    }
}

/**
 * Adds an arbitrary validation. Validations are processed sequentially until first error.
 */
fun InputValidatorBuilder.validation(
    isValid: (String) -> Boolean,
    errorMessageRes: StringResource
) {
    validation(isValid, StringDesc.Resource(errorMessageRes))
}

/**
 * Adds an arbitrary validation. Validations are processed sequentially until first error.
 */
fun InputValidatorBuilder.validation(
    isValid: (String) -> Boolean,
    errorMessage: () -> StringDesc
) {
    validation {
        if (isValid(it)) {
            ValidationResult.Valid
        } else {
            ValidationResult.Invalid(errorMessage.invoke())
        }
    }
}

/**
 * Adds a validation that checks that an input is not blank.
 */
fun InputValidatorBuilder.isNotBlank(errorMessage: StringDesc) {
    validation(
        isValid = { it.isNotBlank() },
        errorMessage
    )
}

/**
 * Adds a validation that checks that an input is not blank.
 */
fun InputValidatorBuilder.isNotBlank(errorMessageRes: StringResource) {
    isNotBlank(StringDesc.Resource(errorMessageRes))
}

/**
 * Adds a validation that checks that an input matches [regex].
 */
fun InputValidatorBuilder.regex(regex: Regex, errorMessage: StringDesc) {
    validation(
        isValid = { regex.matches(it) },
        errorMessage
    )
}

/**
 * Adds a validation that checks that an input matches [regex].
 */
fun InputValidatorBuilder.regex(regex: Regex, errorMessageRes: StringResource) {
    regex(regex, StringDesc.Resource(errorMessageRes))
}

/**
 * Adds a validation that checks that an input has at least given number of symbols.
 */
fun InputValidatorBuilder.minLength(length: Int, errorMessage: StringDesc) {
    validation(
        isValid = { it.length >= length },
        errorMessage
    )
}

/**
 * Adds a validation that checks that an input has at least given number of symbols.
 */
fun InputValidatorBuilder.minLength(length: Int, errorMessageRes: StringResource) {
    minLength(length, StringDesc.Resource(errorMessageRes))
}

/**
 * Adds a validation that checks that an input equals to an input of another input control.
 */
fun InputValidatorBuilder.equalsTo(
    inputControl: ru.mobileup.kmm_form_validation.control.InputControl,
    errorMessage: StringDesc
) {
    validation(
        isValid = { it == inputControl.value.value },
        errorMessage
    )
}

/**
 * Adds a validation that checks that an input equals to an input of another input control.
 */
fun InputValidatorBuilder.equalsTo(
    inputControl: ru.mobileup.kmm_form_validation.control.InputControl,
    errorMessageRes: StringResource
) {
    equalsTo(inputControl, StringDesc.Resource(errorMessageRes))
}