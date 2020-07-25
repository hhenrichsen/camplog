package com.github.hhenrichsen.camplog.util

import com.github.hhenrichsen.camplog.ext.label
import com.github.hhenrichsen.camplog.ext.pluralize
import java.lang.StringBuilder

class DiscreteDuration(
        val years: Long,
        val months: Long,
        val weeks: Long,
        val days: Long,
        val hours: Long,
        val minutes: Long,
        val seconds: Long
) {
    fun asString() : String {
        val builder = StringBuilder()
        if (years != 0L) {
            builder.append(years.label("year") + ", ")
        }
        if (months != 0L) {
            builder.append(months.label("month") + ", ")
        }
        if (weeks != 0L) {
            builder.append(weeks.label("week") + ", ")
        }
        if (days != 0L) {
            builder.append(days.label("day") + ", ")
        }
        if (hours != 0L) {
            builder.append(hours.label("hour") + ", ")
        }
        if (minutes != 0L) {
            builder.append(minutes.label("minute") + ", ")
        }
        if (seconds != 0L) {
            builder.append(seconds.label("second") + ", ")
        }
        return builder.toString().slice(0 until builder.lastIndex - 1)
    }
}