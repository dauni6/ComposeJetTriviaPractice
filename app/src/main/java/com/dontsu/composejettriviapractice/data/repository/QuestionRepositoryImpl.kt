package com.dontsu.composejettriviapractice.data.repository

import android.util.Log
import com.dontsu.composejettriviapractice.data.model.DataOrException
import com.dontsu.composejettriviapractice.data.model.QuestionItem
import com.dontsu.composejettriviapractice.data.network.QuestionApiService
import com.dontsu.composejettriviapractice.di.IODispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class QuestionRepositoryImpl @Inject constructor(
    private val api: QuestionApiService,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher
): QuestionRepository {
    private val dataOrException = DataOrException<ArrayList<QuestionItem>, Boolean, Exception>()

    override suspend fun getAllQuestions(): DataOrException<ArrayList<QuestionItem>, Boolean, Exception> = withContext(ioDispatcher) {
        try {
            dataOrException.loading = true
            dataOrException.data = api.getAllQuestions()
            if (dataOrException.data.toString().isNotEmpty()) dataOrException.loading = false

        } catch (exception: Exception) {
            dataOrException.e = exception
            Log.d("TEST", "getAllQuestions : ${dataOrException.e?.localizedMessage}")
        }

        return@withContext dataOrException
    }

}
