package com.example.imdb

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.imdb.ui.theme.*

@Composable
fun MainScreen(navController: NavHostController) {
    Box(
        Modifier
            .fillMaxSize()
            .padding(30.dp)
    ) {
        Body(Modifier.align(Alignment.Center))
    }

}

@Composable
fun Body(modifier: Modifier) {

    var userEmail by rememberSaveable { mutableStateOf("") }
    var userPassword by rememberSaveable { mutableStateOf("") }
    var isLoginEnable by rememberSaveable { mutableStateOf(false) }

    Column(modifier = modifier) {
        HeaderTitle(Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.size(16.dp))
        UserTitle()
        Spacer(modifier = Modifier.size(6.dp))
        UserEmail(userEmail) { userEmail = it }
        Spacer(modifier = Modifier.size(10.dp))
        PasswordTitle()
        Spacer(modifier = Modifier.size(6.dp))
        UserPassword(userPassword) { userPassword = it }
        Spacer(modifier = Modifier.size(6.dp))
        ForgotPasswordTitle()
        Spacer(modifier = Modifier.size(16.dp))
        LoginButton(isLoginEnable)
        Spacer(modifier = Modifier.size(35.dp))
        TextTitle(Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.size(16.dp))
        SocialIcons()
        Spacer(modifier = Modifier.size(16.dp))
        AccountText(Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.size(16.dp))
        InvitedText(Modifier.align(Alignment.CenterHorizontally))
    }

}

@Composable
fun HeaderTitle(modifier: Modifier) {
    Text(
        text = "IMDb",
        color = Black,
        fontSize = 70.sp,
        fontFamily = RobotoBoldFamily,
        fontWeight = FontWeight.Bold,
        modifier = modifier
    )
}

@Composable
fun UserTitle() {
    Text(
        text = "Usuario",
        color = BlackGrey,
        fontSize = 20.sp,
        fontFamily = RobotoBoldFamily,
        fontWeight = FontWeight.Medium
    )
}

@Composable
fun UserEmail(email: String, onTextChanged: (String) -> Unit) {
    TextField(
        value = email,
        onValueChange = { onTextChanged(it) },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun PasswordTitle() {
    Text(
        text = "Contraseña",
        color = BlackGrey,
        fontSize = 20.sp,
        fontFamily = RobotoBoldFamily,
        fontWeight = FontWeight.Medium
    )
}

@Composable
fun UserPassword(password: String, onTextChanged: (String) -> Unit) {
    TextField(
        value = password,
        onValueChange = { onTextChanged(it) },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun ForgotPasswordTitle() {
    Text(
        text = "¿Olvidaste la contraseña?",
        color = BlackGrey,
        fontSize = 20.sp,
        fontFamily = RobotoBoldFamily,
        fontWeight = FontWeight.Light
    )
}

@Composable
fun LoginButton(loginEnable: Boolean) {
    Button(onClick = {}, enabled = loginEnable, modifier = Modifier.fillMaxWidth(), colors = ButtonDefaults.buttonColors(contentColor = BlackGrey, disabledContentColor = Grey)) {
        Text(
            text = "Login",
            color = White,
            fontSize = 20.sp,
            fontFamily = RobotoBoldFamily,
            fontWeight = FontWeight.Bold
        )

    }

}

@Composable
fun TextTitle(modifier: Modifier) {
    Text(
        text = "Ó podes ingresar con",
        color = Grey,
        fontSize = 25.sp,
        fontFamily = RobotoBoldFamily,
        fontWeight = FontWeight.Light,
        modifier = modifier
    )
}


@Composable
fun SocialIcons() {
    Row(Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Box(modifier = Modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(color = White),
            contentAlignment = Alignment.Center) {
            Image(painter = painterResource(id = R.drawable.apple), contentDescription = "Apple", modifier = Modifier.align(
                Alignment.Center))
        }
        Spacer(modifier = Modifier.size(10.dp))
        Box(modifier = Modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(color = White),
            contentAlignment = Alignment.Center) {
            Image(painter = painterResource(id = R.drawable.facebook), contentDescription = "Apple", modifier = Modifier.align(
                Alignment.Center))
        }
        Spacer(modifier = Modifier.size(10.dp))
        Box(modifier = Modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(color = White),
            contentAlignment = Alignment.Center) {
            Image(painter = painterResource(id = R.drawable.google), contentDescription = "Apple", modifier = Modifier.align(
                Alignment.Center))
        }
    }
}


@Composable
fun AccountText(modifier: Modifier) {
    Row(Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center) {

        Text(
            text = "¿No tenes cuenta?",
            color = Grey,
            fontSize = 25.sp,
            fontFamily = RobotoBoldFamily,
            fontWeight = FontWeight.Light,
            modifier = modifier
        )
        Spacer(modifier = Modifier.size(5.dp))
        Text(
            text = "Regístrate",
            color = BlackGrey,
            fontSize = 25.sp,
            fontFamily = RobotoBoldFamily,
            fontWeight = FontWeight.Light,
            modifier = modifier
        )
        
    }
}


@Composable
fun InvitedText(modifier: Modifier) {
    Text(
        text = "Continuar como invitado",
        color = BlackGrey,
        fontSize = 25.sp,
        fontFamily = RobotoBoldFamily,
        fontWeight = FontWeight.Medium,
        modifier = modifier
    )
}



/*
@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    Body()
}*/
