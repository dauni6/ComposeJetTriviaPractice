package com.dontsu.composejettriviapractice.component

import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dontsu.composejettriviapractice.data.model.QuestionItem
import com.dontsu.composejettriviapractice.navigation.TriviaScreens
import com.dontsu.composejettriviapractice.screens.question.QuestionViewModel
import com.dontsu.composejettriviapractice.util.AppColors
import java.lang.Exception

@Composable
fun Questions(
    viewModel: QuestionViewModel,
    navController: NavController
) {
    val questions = viewModel.data.value.data?.toMutableList() // ArrayList이기 때문에 MutableList()로 캐스팅함

    val questionIndex = remember { mutableStateOf(0) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = AppColors.mDarkPurple
    ) {
        if (viewModel.data.value.loading == true) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(50.dp),
                    color = AppColors.mOffWhite
                )
            }
        } else {
            val question = try {
                questions?.get(questionIndex.value)
            } catch (ex: Exception) {
                null
            }
            if (questions != null && question != null) {
                QuestionDisplay(
                    question = question,
                    questionIndex = questionIndex,
                    viewModel = viewModel,
                    navController = navController
                ) {
                    questionIndex.value = questionIndex.value + 1
                }
            }
        }
    }
}

@Composable
fun QuestionDisplay(
    question: QuestionItem,
    questionIndex: MutableState<Int>,
    viewModel: QuestionViewModel,
    navController: NavController,
    onNextClicked: (Int) -> Unit = { }
) {
    val context = LocalContext.current.applicationContext

    val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
    val choicesState = remember(question) { // remember의 인자로 이렇게 넣는거 도대체 뭐지??
        question.choices.toMutableList()
    }
    val answerState = remember(question) {
        mutableStateOf<Int?>(null)
    }

    val correctAnswerState = remember(question) {
        mutableStateOf<Boolean?>(null)
    }

    val updateAnswer: (Int) -> Unit = remember(question) {
        {
            answerState.value = it
            correctAnswerState.value = choicesState[it] == question.answer
        }
    }

    var chooseState by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(12.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        /*if (questionIndex.value >= 3)*/ ShowProgress(score = questionIndex.value)
        QuestionTracker(counter = questionIndex.value, outOf = viewModel.getTotalQuestionCount())
        DrawDottedLine(pathEffect = pathEffect)

        Column {
            Text(
                text = question.question,
                modifier = Modifier
                    .padding(6.dp)
                    .align(alignment = Alignment.Start)
                    .fillMaxHeight(0.3f),
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 22.sp,
                color = AppColors.mOffWhite
            )
            // 선택될 수 있는 수 만큼 라디오버튼 만들기
            choicesState.forEachIndexed { index, answerText ->
                Row(
                    modifier = Modifier
                        .padding(3.dp)
                        .fillMaxWidth()
                        .height(45.dp)
                        .border(
                            width = 4.dp,
                            brush = Brush.linearGradient(
                                colors = listOf(
                                    AppColors.mOffDarkPurple,
                                    AppColors.mOffDarkPurple
                                )
                            ),
                            shape = RoundedCornerShape(14.dp)
                        )
                        .clip(
                            RoundedCornerShape(
                                topStartPercent = 50,
                                topEndPercent = 50,
                                bottomStartPercent = 50,
                                bottomEndPercent = 50
                            )
                        )
                        .background(Color.Transparent),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = answerState.value == index,
                        onClick = {
                            chooseState = true
                            updateAnswer(index)
                        },
                        modifier = Modifier.padding(start = 16.dp),
                        colors = RadioButtonDefaults.colors(
                            selectedColor = if (correctAnswerState.value == true && index == answerState.value) {
                                Color.Green.copy(alpha = 0.7f)
                            } else {
                                Color.Red.copy(alpha = 0.7f)
                            }
                        )
                    ) // end radio button
                    val annotatedString = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Bold,
                                color = if (correctAnswerState.value == true && index == answerState.value) {
                                    Color.Green
                                } else if (correctAnswerState.value == false && index == answerState.value) {
                                    Color.Red
                                } else {
                                    AppColors.mOffWhite
                                },
                                fontSize = 17.sp
                            )
                        ) {
                            append(answerText)
                        }
                    }
                    Text(text = annotatedString, modifier = Modifier.padding(6.dp))
                }
            }
            Button(
                onClick = {
                    if (chooseState) {
                        onNextClicked(questionIndex.value)
                        if (correctAnswerState.value == true) {
                            viewModel.addRightAnswer(question)
                        }
                        chooseState = false
                    } else {
                        Toast.makeText(context, "정답을 선택해주세요!", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier
                    .padding(3.dp)
                    .align(alignment = Alignment.CenterHorizontally),
                shape = RoundedCornerShape(16.dp),
                colors = buttonColors(
                    backgroundColor = AppColors.mLightBlue
                )
            ) {
                Text(
                    text = "Next",
                    modifier = Modifier.padding(2.dp),
                    color = AppColors.mOffWhite,
                    fontSize = 17.sp
                )
            }
            Spacer(modifier = Modifier.height(100.dp))
            TriviaRightAnswerScreenButton(navController = navController)
        }
    }
}

@Composable
fun DrawDottedLine(
    pathEffect: PathEffect
) {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
    ) {
        drawLine(
            color = AppColors.mLightGray,
            start = Offset(x = 0f, y = 0f),
            end = Offset(x = size.width, y = 0f),
            pathEffect = pathEffect
        )
    }
}

//@Preview
@Composable
fun QuestionTracker(
    counter: Int = 10,
    outOf: Int = 100
) {
    Text(
        text = buildAnnotatedString {
            withStyle(style = ParagraphStyle(textIndent = TextIndent.None)) {
                withStyle(
                    style = SpanStyle(
                        color = AppColors.mLightGray,
                        fontWeight = FontWeight.Bold,
                        fontSize = 27.sp
                    )
                ) {
                    append("Question ${counter + 1}/")
                    withStyle(
                        style = SpanStyle(
                            color = AppColors.mLightGray,
                            fontWeight = FontWeight.Light,
                            fontSize = 14.sp
                        )
                    ) {
                        append("$outOf")
                    }
                }
            }
        },
        modifier = Modifier.padding(20.dp)
    )
}

@Preview
@Composable
fun ShowProgress(score: Int = 12) {
    val gradient = Brush.linearGradient(colors = listOf(Color(0xFFF95075), Color(0xFFBE6BE5)))
    val progressFactor by remember(score) {
        mutableStateOf(score * 0.005f)
    }

    Row(
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth()
            .height(45.dp)
            .border(
                width = 4.dp,
                brush = Brush.linearGradient(
                    colors = listOf(
                        AppColors.mLightPurple,
                        AppColors.mLightPurple
                    )
                ),
                shape = RoundedCornerShape(34.dp)
            )
            .clip(
                RoundedCornerShape(
                    topStartPercent = 50,
                    topEndPercent = 50,
                    bottomEndPercent = 50,
                    bottomStartPercent = 50
                )
            )
            .background(Color.Transparent),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // 스코어미터를 만드는데 버튼을 사용한다. 뭐 다른 Canvas나 그런걸 사용할 줄 알았는데 버튼으로 만들기 쉬워서 그렇다고 한다.
        Button(
            contentPadding = PaddingValues(1.dp),
            onClick = { },
            modifier = Modifier
                .fillMaxWidth(progressFactor)
                .background(brush = gradient),
            enabled = false,
            elevation = null,
            colors = buttonColors(
                backgroundColor = Color.Transparent,
                disabledBackgroundColor = Color.Transparent
            )
        ) {
            Text(
                text = (score * 10).toString(),
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(23.dp))
                    .fillMaxHeight(0.87f)
                    .fillMaxWidth()
                    .padding(6.dp),
                color = AppColors.mOffWhite,
                textAlign = TextAlign.Center
            )
        }
    }

}

@Composable
fun TriviaRightAnswerScreenButton(navController: NavController) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = {
            navController.navigate(route = TriviaScreens.TriviaRightAnswerScreen.name)
        }
    ) {
        Text(
            modifier = Modifier.align(Alignment.CenterVertically),
            text = "Right Answer List =3",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
