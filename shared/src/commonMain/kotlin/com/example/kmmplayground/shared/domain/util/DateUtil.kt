package com.example.kmmplayground.shared.domain.util

import kotlinx.datetime.*

class DateUtil {
    fun now(): LocalDate{
        val currentMoment: Instant = Clock.System.now()
        return currentMoment.toLocalDateTime(TimeZone.UTC).date
    }
}