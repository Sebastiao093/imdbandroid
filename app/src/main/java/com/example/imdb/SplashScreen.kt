package com.example.imdb

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.imdb.navigation.AppScreens
import com.example.imdb.ui.theme.Yellow
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {

    LaunchedEffect(key1 = true){
        delay(5000)
        navController.popBackStack() // limpia el stack de navegaciones para que no pueda volver atras
        navController.navigate(AppScreens.LoginScreen.route)// se llama a la clase de mapeo de pantallas para que vaya al MainScreen
    }//El Coroutine Hace referencia a una tarea asincrona

    Splash()
}

@Composable
fun Splash() {
    Surface(color = Yellow) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            ImageLogo()

        }
    }

}

@Composable
fun ImageLogo() {
    Image(painter = painterResource(id = R.drawable.imdb_logo2x), contentDescription = "logo")
    
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview(){
    Splash()
}