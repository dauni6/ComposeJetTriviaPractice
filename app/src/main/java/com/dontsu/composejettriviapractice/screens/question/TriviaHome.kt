package com.dontsu.composejettriviapractice.screens

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.dontsu.composejettriviapractice.component.Questions
import com.dontsu.composejettriviapractice.screens.question.QuestionViewModel

@Composable
fun TriviaHome(viewModel: QuestionViewModel = hiltViewModel()) {
    Questions(viewModel = viewModel)
}
