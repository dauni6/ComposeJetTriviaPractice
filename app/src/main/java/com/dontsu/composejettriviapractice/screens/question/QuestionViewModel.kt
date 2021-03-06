package com.dontsu.composejettriviapractice.screens.question

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dontsu.composejettriviapractice.data.model.DataOrException
import com.dontsu.composejettriviapractice.data.model.QuestionItem
import com.dontsu.composejettriviapractice.data.repository.QuestionRepository
import com.dontsu.composejettriviapractice.data.repository.RightAnswerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionViewModel @Inject constructor(
    private val repository: QuestionRepository,
    private val rightAnswerRepository: RightAnswerRepository
): ViewModel() {
    var data: MutableState<DataOrException<ArrayList<QuestionItem>, Boolean, Exception>> = mutableStateOf(DataOrException(null, true, Exception("")))
        private set

    init {
        getAllQuestions()
    }

    private fun getAllQuestions() = viewModelScope.launch {
        data.value.loading = true
        data.value = repository.getAllQuestions()
        if (data.value.data.toString().isNotEmpty()) {
            data.value.loading = false
        }

    }

    fun getTotalQuestionCount(): Int {
        return data.value.data?.toMutableList()?.size ?: 0
    }

    fun addRightAnswer(question: QuestionItem) = viewModelScope.launch{
        rightAnswerRepository.addQuestion(question.toEntity())
    }

}
