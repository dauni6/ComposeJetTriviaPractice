package com.dontsu.composejettriviapractice.data.repository

import com.dontsu.composejettriviapractice.data.entity.QuestionEntity
import kotlinx.coroutines.flow.Flow

interface RightAnswerRepository {

    fun getAllQuestions(): Flow<List<QuestionEntity>>

    suspend fun addQuestion(question: QuestionEntity)

    suspend fun deleteQuestion(question: QuestionEntity)

    suspend fun deleteAllQuestions()

}
