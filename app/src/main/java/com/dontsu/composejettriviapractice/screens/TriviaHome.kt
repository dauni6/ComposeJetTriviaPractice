package com.dontsu.composejettriviapractice.screens

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.dontsu.composejettriviapractice.component.Questions


@Composable
fun TriviaHome(viewModel: QuestionViewModel = hiltViewModel()) {
    Questions(viewModel = viewModel)
}
