package com.github.hhenrichsen.camplog.ext

fun Int.pluralize() = when {
        this != 1 -> "s"
        else -> ""
    }