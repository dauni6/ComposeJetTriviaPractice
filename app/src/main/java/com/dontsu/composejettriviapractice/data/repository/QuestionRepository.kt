package com.dontsu.composejettriviapractice.data.repository

import com.dontsu.composejettriviapractice.data.model.DataOrException
import com.dontsu.composejettriviapractice.data.model.QuestionItem

interface QuestionRepository {

    suspend fun getAllQuestions(): DataOrException<ArrayList<QuestionItem>, Boolean, Exception>

}