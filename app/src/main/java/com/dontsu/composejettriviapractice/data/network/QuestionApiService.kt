package com.dontsu.composejettriviapractice.data.network

import com.dontsu.composejettriviapractice.data.model.Question
import retrofit2.http.GET
import javax.inject.Singleton

/**
 * Retrofit은 알아서 suspend로 동작한다. 굳이 withContext(Dispatcher.IO)가 필요할까? 에 대한 글
 * https://thdev.tech/kotlin/2021/01/12/Retrofit-Coroutines/
 * */

@Singleton
interface QuestionApiService {
    @GET("world.json")
    suspend fun getAllQuestions(): Question

}
