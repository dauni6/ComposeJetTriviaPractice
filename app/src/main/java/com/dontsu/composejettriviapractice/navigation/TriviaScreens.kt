package com.dontsu.composejettriviapractice.navigation

enum class TriviaScreens {
    TriviaHomeScreen,
    TriviaRightAnswerScreen;

    companion object {
        fun fromRoute(route: String?): TriviaScreens = when (route?.substringBefore("/")) {
            TriviaHomeScreen.name -> TriviaHomeScreen
            TriviaRightAnswerScreen.name ->TriviaRightAnswerScreen
            null -> TriviaHomeScreen
            else -> throw IllegalArgumentException("Route $route is not recognized")
        }
    }

}
