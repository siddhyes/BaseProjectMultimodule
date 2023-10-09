package com.nst.baseproject.common.util

import com.google.gson.Gson

fun String.capitalized(): String {
    val value = this.lowercase()
    return sequence {
        var startIndex = 0
        while (startIndex < value.length) {
            val endIndex = value.indexOf(' ', startIndex).takeIf { it > 0 }
                ?: value.length
            yield(value.substring(startIndex, endIndex))
            startIndex = endIndex + 1
        }
    }.joinToString(" ") { it.replaceFirstChar(Char::uppercaseChar) }
}

fun String.safeDouble(): Double {
    return this.toDoubleOrNull() ?: 0.0
}

fun String.safeInt(): Int {
    return this.toIntOrNull() ?: 0
}

inline fun <reified T> genericCastOrNull(anything: Any?): T? {
    return anything as? T
}

private inline fun <reified T : Any> Gson.fromMap(map: Map<*, *>): T? {
    return fromJson(toJsonTree(map), T::class.java)
}