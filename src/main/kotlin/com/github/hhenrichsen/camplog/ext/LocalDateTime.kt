package com.github.hhenrichsen.camplog.ext

import com.github.hhenrichsen.camplog.util.DiscreteDuration
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.temporal.ChronoUnit

fun LocalDateTime.between(other: LocalDateTime): DiscreteDuration {
    val start = minOf(this, other).atZone(ZoneId.systemDefault())
    val end = maxOf(this, other).atZone(ZoneId.systemDefault())

    val years = start.until(end, ChronoUnit.YEARS)
    var tick = start.plusYears(years)

    val months = tick.until(end, ChronoUnit.MONTHS)
    tick = tick.plusMonths(months)

    val weeks = tick.until(end, ChronoUnit.WEEKS)
    tick = tick.plusWeeks(weeks)

    val days = tick.until(end, ChronoUnit.DAYS)
    tick = tick.plusDays(days)

    val hours = tick.until(end, ChronoUnit.HOURS)
    tick = tick.plusHours(hours)

    val minutes = tick.until(end, ChronoUnit.MINUTES)
    tick = tick.plusMinutes(minutes)

    val seconds = tick.until(end, ChronoUnit.SECONDS)
    return DiscreteDuration(years, months, weeks, days, hours, minutes, seconds)
}