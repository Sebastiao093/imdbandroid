package com.example.imdb

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.example.imdb.ui.theme.*

@Composable
fun RegisterScreen(navController: NavHostController) {
    Surface(color = White100) {
        Box(
            Modifier
                .fillMaxSize()
                .padding(10.dp)
                .background(color = White100)
        ) {
            Header(Modifier.align(Alignment.TopStart), navController)
            Box(
                Modifier
                    .fillMaxSize()
                    .padding(25.dp)
            ) {
                BodyRegister(Modifier.align(Alignment.TopStart))
            }

        }
    }
}

@Composable
fun BodyRegister(modifier: Modifier) {

    var userNameRegister by rememberSaveable { mutableStateOf("") }
    var userEmailRegister by rememberSaveable { mutableStateOf("") }
    var userPasswordRegister by rememberSaveable { mutableStateOf("") }
    var isLoginEnable by rememberSaveable { mutableStateOf(false) }


    Column(modifier = modifier) {
        IconLogo(
            Modifier
                .align(Alignment.Start)
                .padding(top = 25.dp)
        )
        Spacer(modifier = Modifier.size(30.dp))
        AccountTitle()
        Spacer(modifier = Modifier.size(10.dp))
        UserNameRegister(userNameRegister) { userNameRegister = it }
        Spacer(modifier = Modifier.size(10.dp))
        EmailRegister(userEmailRegister) { userEmailRegister = it }
        Spacer(modifier = Modifier.size(10.dp))
        PasswordRegister(userPasswordRegister) { userPasswordRegister = it }
        Spacer(modifier = Modifier.size(10.dp))
        PasswordTitleText()
        Spacer(modifier = Modifier.size(25.dp))
        AcceptButton(isLoginEnable)
        Spacer(modifier = Modifier.size(35.dp))
    }
}

@Composable
fun Header(modifier: Modifier, navController: NavHostController) {
    Icon(
        imageVector = Icons.Default.ArrowBack,
        contentDescription = "go back",
        modifier.clickable { navController.navigateUp() },
        tint = Black
    )
}

@Composable
fun IconLogo(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.imdb_logo),
        contentDescription = "logo",
        modifier = modifier
    )
}

@Composable
fun AccountTitle() {
    Text(
        text = "Crear una cuenta",
        color = BlackGrey,
        fontSize = 20.sp,
        fontFamily = RobotoBoldFamily,
        fontWeight = FontWeight.Medium
    )
}

@Composable
fun UserNameRegister(name: String, onTextChanged: (String) -> Unit) {
    OutlinedTextField(
        value = name,
        onValueChange = { onTextChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        maxLines = 1,
        singleLine = true,
        label = { Text(text = "Nombre") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Grey70,
            backgroundColor = White100,
            focusedIndicatorColor = Grey70,
            unfocusedIndicatorColor = Grey70,
            cursorColor = Grey70,
            focusedLabelColor = Grey70
        ),
        textStyle = TextStyle(
            fontFamily = RobotoBoldFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
        ),
        shape = RoundedCornerShape(10.dp),

        )
}

@Composable
fun EmailRegister(email: String, onTextChanged: (String) -> Unit) {
    OutlinedTextField(
        value = email,
        onValueChange = { onTextChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        maxLines = 1,
        singleLine = true,
        label = { Text(text = "Correo electrónico") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Grey70,
            backgroundColor = White100,
            focusedIndicatorColor = Grey70,
            unfocusedIndicatorColor = Grey70,
            cursorColor = Grey70,
            focusedLabelColor = Grey70
        ),
        textStyle = TextStyle(
            fontFamily = RobotoBoldFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
        ),
        shape = RoundedCornerShape(10.dp)
    )
}

@Composable
fun PasswordRegister(password: String, onTextChanged: (String) -> Unit) {
    var passwordVisibility by remember { mutableStateOf(false) }
    OutlinedTextField(
        value = password,
        onValueChange = { onTextChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        maxLines = 1,
        singleLine = true,
        label = { Text(text = "Contraseña") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Grey70,
            backgroundColor = White100,
            focusedIndicatorColor = Grey70,
            unfocusedIndicatorColor = Grey70,
            cursorColor = Grey70,
            focusedLabelColor = Grey70
        ),
        textStyle = TextStyle(
            fontFamily = RobotoBoldFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
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
fun PasswordTitleText() {
    Text(
        text = "La contraseña debe contener 8 caracteres",
        color = Grey,
        fontSize = 12.sp,
        fontFamily = RobotoBoldFamily,
        fontWeight = FontWeight.Light
    )
}

@Composable
fun AcceptButton(loginEnable: Boolean) {
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
            text = "Aceptar",
            color = White,
            fontSize = 20.sp,
            fontFamily = RobotoBoldFamily,
            fontWeight = FontWeight.Medium
        )

    }

}

/*
@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen()
}*/
