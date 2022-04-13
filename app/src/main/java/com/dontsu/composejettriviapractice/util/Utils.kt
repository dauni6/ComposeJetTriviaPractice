package com.dontsu.composejettriviapractice.util

import java.text.SimpleDateFormat
import java.util.*

fun Long.toDate(): String {
    val date = Date(this)
    val format = SimpleDateFormat("EEE, yyyy MM-dd hh:mm aaa", Locale.getDefault())

    return format.format(date)
}
