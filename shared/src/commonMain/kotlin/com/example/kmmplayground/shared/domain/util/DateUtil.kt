package com.example.kmmplayground.shared.domain.util

import kotlinx.datetime.*

class DateUtil {
    fun now(): LocalDate{
        val currentMoment: Instant = Clock.System.now()
        return currentMoment.toLocalDateTime(TimeZone.UTC).date
    }

    fun toLocalDate(date: Double): LocalDate {
        return Instant.fromEpochSeconds(date.toLong()).toLocalDateTime(TimeZone.UTC).date
    }

    fun toEpochSeconds(date: LocalDate): Double{
        return date.atStartOfDayIn(TimeZone.UTC).epochSeconds.toDouble()
    }
}