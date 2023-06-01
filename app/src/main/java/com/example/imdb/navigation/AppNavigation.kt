package com.example.imdb.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.imdb.login.ui.LoginScreen
import com.example.imdb.RegisterScreen
import com.example.imdb.SearchScreen
import com.example.imdb.splash.ui.SplashScreen
import com.example.imdb.splash.ui.SplashViewModel
import com.example.imdb.login.ui.LoginViewModel
import com.example.imdb.register.ui.RegisterViewModel
import com.example.imdb.search.ui.SearchViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = AppScreens.SplashScreen.route,
        builder = {
            composable(AppScreens.SplashScreen.route){
                SplashScreen(navController, SplashViewModel())
            }
            composable(AppScreens.LoginScreen.route){
                LoginScreen(navController, LoginViewModel())
            }
            composable(AppScreens.RegisterScreen.route){
                RegisterScreen(navController, RegisterViewModel())
            }
            composable(AppScreens.SearchScreen.route){
                SearchScreen(navController, SearchViewModel())
            }
        })
}