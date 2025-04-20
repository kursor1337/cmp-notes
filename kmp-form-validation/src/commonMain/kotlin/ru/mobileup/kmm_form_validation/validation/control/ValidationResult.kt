package ru.mobileup.kmm_form_validation.validation.control

import com.kursor.chronicles_of_ww2.core.strings.StringDesc

/**
 * Represents a result of validation.
 */
sealed class ValidationResult {

    /**
     * An input is valid.
     */
    object Valid : ValidationResult()

    /**
     * Validation was skipped.
     */
    object Skipped : ValidationResult()

    /**
     * An input is invalid.
     */
    data class Invalid(val errorMessage: StringDesc) : ValidationResult()
}