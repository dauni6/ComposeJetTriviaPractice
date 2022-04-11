package com.dontsu.composejettriviapractice.screens.rightanswer

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dontsu.composejettriviapractice.data.entity.QuestionEntity
import com.dontsu.composejettriviapractice.util.AppColors

@Composable
fun TriviaRightAnswerScreen(
    navController: NavController,
    viewModel: RightAnswerViewModel = hiltViewModel()
) {
    val rightAnswerList = viewModel.rightAnswerList.collectAsState().value

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = AppColors.mDarkPurple,
                elevation = 4.dp
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Icon(
                        modifier = Modifier.clickable {
                            navController.popBackStack()
                        },
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "뒤로가기",
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(100.dp))
                    Text(text = "Right Answer List", color = Color.Black, style = MaterialTheme.typography.h5)
                }
            }
        }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(AppColors.mDarkPurple)
        ) {
            RightAnswerContent(
                viewModel = viewModel,
                rightAnswerList = rightAnswerList
            )
        }
    }
}

@Composable
fun RightAnswerContent(
    viewModel: RightAnswerViewModel,
    rightAnswerList: List<QuestionEntity>
) {

}
