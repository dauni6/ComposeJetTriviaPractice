package com.dontsu.composejettriviapractice.data.repository

import com.dontsu.composejettriviapractice.data.entity.QuestionEntity
import com.dontsu.composejettriviapractice.data.local.QuestionDatabaseDao
import com.dontsu.composejettriviapractice.di.IODispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RightAnswerRepositoryImpl @Inject constructor(
    private val questionDatabaseDao: QuestionDatabaseDao,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher
): RightAnswerRepository {

    override fun getAllQuestions(): Flow<List<QuestionEntity>> = questionDatabaseDao.getAllQuestions().flowOn(ioDispatcher).conflate()

    override suspend fun addQuestion(question: QuestionEntity) = questionDatabaseDao.insertQuestion(question = question)

    override suspend fun deleteQuestion(question: QuestionEntity) = questionDatabaseDao.deleteQuestion(question = question)

    override suspend fun deleteAllQuestions() = questionDatabaseDao.deleteAllQuestion()

}
