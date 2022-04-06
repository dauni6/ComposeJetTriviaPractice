package com.dontsu.composejettriviapractice.converter

import androidx.room.TypeConverter
import java.util.*

//@ProvidedTypeConverter
object DateTypeConverter {

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

}
