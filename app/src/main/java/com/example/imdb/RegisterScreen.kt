package com.example.imdb

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.imdb.ui.theme.Black
import com.example.imdb.ui.theme.White

@Composable
fun RegisterScreen(navController: NavHostController) {
    Surface(color = White) {
        Box(
            Modifier
                .fillMaxSize()
                .padding(10.dp)
                .background(color = White)
        ) {
            Header(Modifier.align(Alignment.TopStart))
        }
    }
}

@Composable
fun Header(modifier: Modifier) {
    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "go back", modifier.clickable {  }, tint = Black)

}

/*
@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen()
}*/
