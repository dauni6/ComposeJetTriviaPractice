package com.dontsu.composejettriviapractice.screens.rightanswer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dontsu.composejettriviapractice.data.entity.QuestionEntity
import com.dontsu.composejettriviapractice.data.repository.RightAnswerRepository
import com.dontsu.composejettriviapractice.di.IODispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RightAnswerViewModel @Inject constructor(
    private val repository: RightAnswerRepository,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher
): ViewModel() {
    private val _rightAnswerList = MutableStateFlow<List<QuestionEntity>>(emptyList())
    val rightAnswerList = _rightAnswerList.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getAllQuestions().distinctUntilChanged()
                .collect { listOfRightAnswers ->
                    if (listOfRightAnswers.isNullOrEmpty()) {
                        _rightAnswerList.value = emptyList()
                    } else {
                        _rightAnswerList.value = listOfRightAnswers
                    }
                }
        }
    }

    fun removeRightAnswer(rightAnswer: QuestionEntity) = viewModelScope.launch{
        repository.deleteQuestion(rightAnswer)
    }

}
