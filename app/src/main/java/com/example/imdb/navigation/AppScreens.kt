package com.example.imdb.navigation

sealed class AppScreens(val route: String) {
    object SplashScreen: AppScreens(route = "splash_screen")
    object LoginScreen: AppScreens(route = "login_screen")
    object RegisterScreen: AppScreens(route = "register_screen")
    object SearchScreen: AppScreens(route = "search_screen")
}