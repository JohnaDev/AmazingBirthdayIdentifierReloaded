package com.example.amazingbirthdayidentifierreloaded.viewmodel

import androidx.lifecycle.ViewModel
import com.example.amazingbirthdayidentifierreloaded.model.BirthdayState
import com.example.amazingbirthdayidentifierreloaded.model.BirthdayUtils.isSameDayAndMonth
import com.example.amazingbirthdayidentifierreloaded.model.Type
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

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

}