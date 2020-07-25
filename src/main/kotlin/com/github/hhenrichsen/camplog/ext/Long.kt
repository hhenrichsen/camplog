package com.github.hhenrichsen.camplog.ext

fun Long.pluralize() = when {
        this != 1L -> "s"
        else -> ""
    }

fun Long.label(singular: String) = "$this $singular${this.pluralize()}"

fun Long.label(singular: String, plural: String) = when {
    this != 1L -> "$this $plural"
    else -> "$this $singular"
}