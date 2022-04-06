package com.dontsu.composejettriviapractice.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dontsu.composejettriviapractice.converter.DateTypeConverter
import com.dontsu.composejettriviapractice.converter.QuestionTypeConverter
import com.dontsu.composejettriviapractice.converter.UUIDTypeConverter
import com.dontsu.composejettriviapractice.data.entity.QuestionEntity

@Database(entities = [QuestionEntity::class], version = 1, exportSchema = false)
@TypeConverters(DateTypeConverter::class, UUIDTypeConverter::class, QuestionTypeConverter::class)
abstract class QuestionDatabase : RoomDatabase() {

    abstract fun questionDao(): QuestionDatabaseDao

}
