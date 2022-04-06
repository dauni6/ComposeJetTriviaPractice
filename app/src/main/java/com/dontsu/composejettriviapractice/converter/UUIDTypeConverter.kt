package com.dontsu.composejettriviapractice.converter

import androidx.room.TypeConverter
import java.util.*

//@ProvidedTypeConverter
object UUIDTypeConverter {

    @TypeConverter
    fun fromUUID(uuid: UUID): String {
        return uuid.toString()
    }

    @TypeConverter
    fun uuidFromString(string: String?): UUID? {
        return UUID.fromString(string)
    }
}
