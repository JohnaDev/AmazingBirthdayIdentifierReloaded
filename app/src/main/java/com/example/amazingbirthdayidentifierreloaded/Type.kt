package com.example.amazingbirthdayidentifierreloaded

/**
 * Represents the distinct states of the birthday verification UI.
 *
 * This enum is used by [BirthdayState] to determine which message or UI component
 * to display to the user at any given time.
 */
enum class Type {
    /**
     * The initial state before any date has been selected.
     * The UI should prompt the user to enter their date of birth.
     */
    ASK,

    /**
     * The state indicating that the selected date matches today's date (day and month).
     * The UI should display a celebratory "Happy Birthday" message.
     */
    TRUE,

    /**
     * The state indicating that the selected date does not match today's date.
     * The UI should inform the user that it is not their birthday.
     */
    FALSE
}