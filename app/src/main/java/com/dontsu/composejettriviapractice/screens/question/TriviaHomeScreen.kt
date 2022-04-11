package com.dontsu.composejettriviapractice.screens.question

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dontsu.composejettriviapractice.component.Questions

@Composable
fun TriviaHome(
    viewModel: QuestionViewModel = hiltViewModel(),
    navController: NavController
) {
    Questions(
        viewModel = viewModel,
        navController = navController
    )
}
