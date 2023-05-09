package com.example.imdb.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.imdb.MainScreen
import com.example.imdb.RegisterScreen
import com.example.imdb.SplashScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = AppScreens.SplashScreen.route,
        builder = {
            composable(AppScreens.SplashScreen.route){
                SplashScreen(navController)
            }
            composable(AppScreens.MainScreen.route){
                MainScreen(navController)
            }
            composable(AppScreens.RegisterScreen.route){
                RegisterScreen(navController)
            }
        })
}