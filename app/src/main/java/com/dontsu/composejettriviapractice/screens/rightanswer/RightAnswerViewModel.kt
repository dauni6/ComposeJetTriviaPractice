package com.dontsu.composejettriviapractice.screens.rightanswer

import androidx.lifecycle.ViewModel
import com.dontsu.composejettriviapractice.data.repository.RightAnswerRepository
import com.dontsu.composejettriviapractice.di.IODispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@HiltViewModel
class RightAnswerViewModel @Inject constructor(
    private val repository: RightAnswerRepository,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher
): ViewModel() {

}
