package com.dontsu.composejettriviapractice.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant
import java.util.*

@Entity(tableName = "question_table")
data class QuestionEntity(
    @PrimaryKey
    @ColumnInfo(name = "question_id")
    val id: UUID = UUID.randomUUID(),

    @ColumnInfo(name = "question_answer")
    val answer: String,

    @ColumnInfo(name = "question_category")
    val category: String,

    @ColumnInfo(name = "question_choices")
    val choices: List<String>,

    @ColumnInfo(name = "question_question")
    val question: String,

    @ColumnInfo(name = "question_date")
    val date: Date = Date.from(Instant.now())

)
