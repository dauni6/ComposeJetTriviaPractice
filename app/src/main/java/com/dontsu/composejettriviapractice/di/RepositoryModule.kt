package com.dontsu.composejettriviapractice.di

import com.dontsu.composejettriviapractice.data.repository.QuestionRepository
import com.dontsu.composejettriviapractice.data.repository.QuestionRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideQuestionRepository(
        questionRepositoryImpl: QuestionRepositoryImpl
    ): QuestionRepository

}