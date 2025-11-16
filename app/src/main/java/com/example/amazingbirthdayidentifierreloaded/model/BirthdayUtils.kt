package com.example.amazingbirthdayidentifierreloaded.model

import java.time.Instant
import java.time.ZoneId

object BirthdayUtils {

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
    fun isSameDayAndMonth(date1Millis: Long, date2Millis: Long): Boolean {
        // Convert milliseconds to LocalDate objects, which represent a date without time-of-day.
        val date1 =
            Instant.ofEpochMilli(date1Millis).atZone(ZoneId.systemDefault()).toLocalDate()
        val date2 =
            Instant.ofEpochMilli(date2Millis).atZone(ZoneId.systemDefault()).toLocalDate()

        // Compare the month and the day of the month for equality.
        return date1.month == date2.month && date1.dayOfMonth == date2.dayOfMonth
    }
}