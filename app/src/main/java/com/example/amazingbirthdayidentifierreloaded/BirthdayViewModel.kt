package com.example.amazingbirthdayidentifierreloaded

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.Instant
import java.time.ZoneId

open class BirthdayViewModel : ViewModel() {
    private val _state = MutableStateFlow(BirthdayState())
    open val state: StateFlow<BirthdayState> = _state.asStateFlow()

    /**
     * Checks if the provided birthdate (day and month) matches today's date.
     * @param birthdateMillis The selected date of birth in milliseconds since the epoch.
     */
    open fun checkIsBirthday(birthdateMillis: Long) {
        val isBirthdayToday = isSameDayAndMonth(birthdateMillis, System.currentTimeMillis())
        _state.value =
            _state.value.copy(isBirthDay = if (isBirthdayToday) Type.TRUE else Type.FALSE)
    }

    /**
     * Compares two dates to see if they fall on the same day and month, ignoring the year.
     * This uses the modern java.time API.
     */
    private fun isSameDayAndMonth(date1Millis: Long, date2Millis: Long): Boolean {
        // Convert milliseconds to LocalDate objects
        val date1 =
            Instant.ofEpochMilli(date1Millis).atZone(ZoneId.systemDefault()).toLocalDate()
        val date2 =
            Instant.ofEpochMilli(date2Millis).atZone(ZoneId.systemDefault()).toLocalDate()

        // Compare the month and the day of the month
        return date1.month == date2.month && date1.dayOfMonth == date2.dayOfMonth
    }

}