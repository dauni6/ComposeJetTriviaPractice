package com.dontsu.composejettriviapractice.screens.rightanswer

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dontsu.composejettriviapractice.data.entity.QuestionEntity
import com.dontsu.composejettriviapractice.util.AppColors
import com.dontsu.composejettriviapractice.util.toDate

@Composable
fun TriviaRightAnswerScreen(
    navController: NavController,
    viewModel: RightAnswerViewModel = hiltViewModel()
) {
    Log.d("TEST", "TriviaRightAnswerScreen() is called.")
    val rightAnswerList = viewModel.rightAnswerList.collectAsState().value

    Log.d("TEST", "List size = ${rightAnswerList.size}")

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
                    Text(text = "Right Answer List", color = Color.White, style = MaterialTheme.typography.h5)
                }
            }
        }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(AppColors.mDarkPurple)
        ) {
            if (rightAnswerList.isNullOrEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = "맞춘 정답이 없습니다.",
                        fontWeight = FontWeight.Bold,
                        fontSize = 32.sp,
                        textAlign = TextAlign.Center
                    )
                }
            } else {
                RightAnswerContent(
                    viewModel = viewModel,
                    rightAnswerList = rightAnswerList
                )
            }
        }
    }
}

@Composable
fun RightAnswerContent(
    viewModel: RightAnswerViewModel,
    rightAnswerList: List<QuestionEntity>
) {
    LazyColumn {
        items(rightAnswerList) { rightAnswer ->
            RightAnswerRow(viewModel = viewModel, rightAnswer = rightAnswer)
        }
    }
}

@Composable
fun RightAnswerRow(
    viewModel: RightAnswerViewModel,
    rightAnswer: QuestionEntity
) {
    val context = LocalContext.current

    Surface(
        modifier = Modifier
            .padding(4.dp)
            .clip(RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp))
            .fillMaxWidth(),
        color = Color(0xFFDFE6EB),
        elevation = 6.dp
    ) {
        Column(
            modifier = Modifier
                .clickable {
                    viewModel.removeRightAnswer(rightAnswer)
                    Toast
                        .makeText(context, "삭제했습니다요", Toast.LENGTH_SHORT)
                        .show()
                }
                .padding(horizontal = 14.dp, vertical = 6.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = rightAnswer.question,
                style = MaterialTheme.typography.subtitle2,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = rightAnswer.answer,
                style = MaterialTheme.typography.subtitle1,
            )
            Text(
                text = rightAnswer.date.time.toDate(), // ex) Jul 18, Wed, Jul 23 1999...
                style = MaterialTheme.typography.caption,
            )
        }
    }

}

