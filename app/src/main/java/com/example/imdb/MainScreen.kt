package com.example.imdb

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.imdb.navigation.AppScreens
import com.example.imdb.ui.theme.*

@Composable
fun MainScreen(navController: NavHostController) {
    val navController = navController
    Box(
        Modifier
            .fillMaxSize()
            .padding(30.dp)
    ) {
        Body(Modifier.align(Alignment.Center), navController)
    }

}

@Composable
fun Body(modifier: Modifier, navController: NavHostController) {

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
        AccountText(Modifier.align(Alignment.CenterHorizontally), navController)
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
        fontSize = 16.sp,
        fontFamily = RobotoBoldFamily,
        fontWeight = FontWeight.Medium
    )
}

@Composable
fun UserEmail(email: String, onTextChanged: (String) -> Unit) {
    TextField(
        value = email,
        onValueChange = { onTextChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Grey70,
            backgroundColor = White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = Grey70,
        ),
        textStyle = TextStyle(
            fontFamily = RobotoBoldFamily,
            fontWeight = FontWeight.Light,
            fontSize = 14.sp,
        ),
        shape = RoundedCornerShape(10.dp)
    )
}

@Composable
fun PasswordTitle() {
    Text(
        text = "Contraseña",
        color = BlackGrey,
        fontSize = 16.sp,
        fontFamily = RobotoBoldFamily,
        fontWeight = FontWeight.Medium
    )
}

@Composable
fun UserPassword(password: String, onTextChanged: (String) -> Unit) {
    var passwordVisibility by remember { mutableStateOf(false) }
    TextField(
        value = password,
        onValueChange = { onTextChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Grey70,
            backgroundColor = White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = Grey70,
        ),
        textStyle = TextStyle(
            fontFamily = RobotoBoldFamily,
            fontWeight = FontWeight.Light,
            fontSize = 14.sp,
        ),
        shape = RoundedCornerShape(10.dp),
        trailingIcon = {
            val icon =
                if (passwordVisibility) Icons.Filled.VisibilityOff else Icons.Filled.Visibility
            IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                Icon(imageVector = icon, contentDescription = "show password")
            }
        },
        visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation()
    )
}

@Composable
fun ForgotPasswordTitle() {
    Text(
        text = "¿Olvidaste la contraseña?",
        color = BlackGrey,
        fontSize = 12.sp,
        fontFamily = RobotoBoldFamily,
        fontWeight = FontWeight.Light
    )
}

@Composable
fun LoginButton(loginEnable: Boolean) {
    Button(
        onClick = {},
        enabled = loginEnable,
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = BlackGrey,
            disabledBackgroundColor = Grey,
            contentColor = BlackGrey,
            disabledContentColor = Grey,
        ),
        shape = RoundedCornerShape(10.dp)
    ) {
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
        fontSize = 16.sp,
        fontFamily = RobotoBoldFamily,
        fontWeight = FontWeight.Light,
        modifier = modifier
    )
}


@Composable
fun SocialIcons() {
    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(color = White),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.apple),
                contentDescription = "Apple",
                modifier = Modifier.align(
                    Alignment.Center
                )
            )
        }
        Spacer(modifier = Modifier.size(10.dp))
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(color = White),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.facebook),
                contentDescription = "Apple",
                modifier = Modifier.align(
                    Alignment.Center
                )
            )
        }
        Spacer(modifier = Modifier.size(10.dp))
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(color = White),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.google),
                contentDescription = "Apple",
                modifier = Modifier.align(
                    Alignment.Center
                )
            )
        }
    }
}


@Composable
fun AccountText(modifier: Modifier, navController: NavHostController) {
    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        Text(
            text = "¿No tenes cuenta?",
            color = Grey,
            fontSize = 16.sp,
            fontFamily = RobotoBoldFamily,
            fontWeight = FontWeight.Light,
            modifier = modifier
        )
        Spacer(modifier = Modifier.size(5.dp))
        Text(
            text = "Regístrate",
            color = BlackGrey,
            fontSize = 16.sp,
            fontFamily = RobotoBoldFamily,
            fontWeight = FontWeight.Light,
            modifier = modifier.clickable { navController.navigate(AppScreens.RegisterScreen.route) },

            )

    }
}


@Composable
fun InvitedText(modifier: Modifier) {
    Text(
        text = "Continuar como invitado",
        color = BlackGrey,
        fontSize = 16.sp,
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
