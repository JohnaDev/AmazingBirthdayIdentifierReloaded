package com.example.amazingbirthdayidentifierreloaded.viewmodel

import androidx.lifecycle.ViewModel
import com.example.amazingbirthdayidentifierreloaded.model.BirthdayState
import com.example.amazingbirthdayidentifierreloaded.model.Type
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.Instant
import java.time.ZoneId

/**
 * Manages the business logic for the birthday verification feature.
 *
 * This ViewModel is responsible for holding the UI state ([com.example.amazingbirthdayidentifierreloaded.model.BirthdayState]),
 * processing user actions, and performing the core logic of checking if a
 * selected date is the user's birthday. It is designed to survive configuration
 * changes and separates the logic from the UI (the Activity/Composable).
 *
 * The UI observes the public [state] property to reactively update itself based
 * on changes.
 */
open class BirthdayViewModel : ViewModel() {

    /**
     * The private, mutable state flow that holds the current UI state.
     * Only the ViewModel can modify this state.
     */
    private val _state = MutableStateFlow(BirthdayState())

    /**
     * The public, read-only state flow that the UI observes for updates.
     * It exposes the [_state] as an immutable [kotlinx.coroutines.flow.StateFlow] to prevent external modification.
     */
    open val state: StateFlow<BirthdayState> = _state.asStateFlow()

    /**
     * Checks if the provided date has the same day and month as today and updates the UI state accordingly.
     *
     * This function takes a date in milliseconds, compares it to the current system date,
     * and updates the [state] to [com.example.amazingbirthdayidentifierreloaded.model.Type.TRUE] if it's a birthday, or [com.example.amazingbirthdayidentifierreloaded.model.Type.FALSE] otherwise.
     *
     * @param birthdateMillis The selected date of birth in milliseconds since the epoch,
     *                        typically from a date picker.
     */
    open fun checkIsBirthday(birthdateMillis: Long) {
        val isBirthdayToday = isSameDayAndMonth(birthdateMillis, System.currentTimeMillis())
        _state.value =
            _state.value.copy(isBirthDay = if (isBirthdayToday) Type.TRUE else Type.FALSE)
    }

    /**
     * Compares two dates to determine if they fall on the same day and month, ignoring the year.
     *
     * This utility function is the core of the birthday check logic. It uses the modern
     * `java.time` API to ensure accurate date comparisons across different time zones
     * by converting the dates to the system's default time zone.
     *
     * @param date1Millis The first date to compare, in milliseconds since the epoch.
     * @param date2Millis The second date to compare, in milliseconds since the epoch.
     * @return `true` if the day and month of both dates are the same, `false` otherwise.
     */
    private fun isSameDayAndMonth(date1Millis: Long, date2Millis: Long): Boolean {
        // Convert milliseconds to LocalDate objects, which represent a date without time-of-day.
        val date1 =
            Instant.ofEpochMilli(date1Millis).atZone(ZoneId.systemDefault()).toLocalDate()
        val date2 =
            Instant.ofEpochMilli(date2Millis).atZone(ZoneId.systemDefault()).toLocalDate()

        // Compare the month and the day of the month for equality.
        return date1.month == date2.month && date1.dayOfMonth == date2.dayOfMonth
    }

}