package ru.mts.mtstv.getshop.utils

import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*

fun calculateDelay(from: Double = System.currentTimeMillis().toDouble(), toInSeconds: Double): Long {
    return calculateDelay(from.toLong(), toInSeconds.toLong())
}

fun calculateDelay(from: Long = System.currentTimeMillis(), toInSeconds: Long): Long {
    val updatedTo = (toInSeconds * 1000)
    return Math.max(updatedTo - from, 0)
}

fun fromEpochToISO8601(epoch: Long): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss'Z'", Locale.getDefault()).apply {
        timeZone = TimeZone.getTimeZone("GMT-0")
    }
    val netDate = Date(epoch * 1000)
    return dateFormat.format(netDate)
}

fun showErrorLog(errorText: String) {
    Timber.e(errorText)
}