package com.dontsu.composejettriviapractice.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dontsu.composejettriviapractice.screens.question.TriviaHome
import com.dontsu.composejettriviapractice.screens.rightanswer.TriviaRightAnswerScreen

@Composable
fun TriviaNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = TriviaScreens.TriviaHomeScreen.name
    ) {
        composable(TriviaScreens.TriviaHomeScreen.name) {
            TriviaHome(navController = navController)
        }

        composable(TriviaScreens.TriviaRightAnswerScreen.name) {
            TriviaRightAnswerScreen(navController = navController)
        }
    }


}