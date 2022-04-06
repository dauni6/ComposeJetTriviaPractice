package com.dontsu.composejettriviapractice.data.local

import androidx.room.*
import com.dontsu.composejettriviapractice.data.entity.QuestionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface QuestionDatabaseDao {

    @Query("SELECT * from question_table")
    fun getAllQuestions(): Flow<List<QuestionEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestion(question: QuestionEntity)

    @Query("DELETE from question_table")
    suspend fun deleteAllQuestion()

    @Delete
    suspend fun deleteQuestion(question: QuestionEntity)

}
