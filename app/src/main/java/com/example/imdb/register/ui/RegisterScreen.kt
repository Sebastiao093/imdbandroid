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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.navigation.NavHostController
import com.example.imdb.register.ui.RegisterViewModel
import com.example.imdb.ui.theme.*

@Composable
fun RegisterScreen(navController: NavHostController, registerViewModel: RegisterViewModel) {
    Surface(color = White100) {
        Box(
            Modifier
                .fillMaxSize()
                .padding(dimensionResource(id = R.dimen.padding_10dp))
                .background(color = White100)
        ) {
            Header(Modifier.align(Alignment.TopStart), navController)
            Box(
                Modifier
                    .fillMaxSize()
                    .padding(dimensionResource(id = R.dimen.padding_24dp))
            ) {
                BodyRegister(Modifier.align(Alignment.TopStart), registerViewModel)
            }

        }
    }
}

@Composable
fun BodyRegister(modifier: Modifier, registerViewModel: RegisterViewModel) {

    val userNameRegister by registerViewModel.nameRegister.observeAsState(initial = "")
    val userEmailRegister by registerViewModel.emailRegister.observeAsState(initial = "")
    val userPasswordRegister by registerViewModel.passwordRegister.observeAsState(initial = "")
    val isLoginEnableRegister by registerViewModel.isLoginEnableRegister.observeAsState(initial = false)

    Column(modifier = modifier) {
        IconLogo(
            Modifier
                .align(Alignment.Start)
                .padding(top = dimensionResource(id = R.dimen.padding_24dp))
        )
        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.space_30dp)))
        AccountTitle()
        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.space_10dp)))
        UserNameRegister(userNameRegister) { registerViewModel.onRegisterChanged(name = it, userEmailRegister, userPasswordRegister) }
        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.space_10dp)))
        EmailRegister(userEmailRegister) { registerViewModel.onRegisterChanged(userNameRegister, email = it, userPasswordRegister) }
        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.space_10dp)))
        PasswordRegister(userPasswordRegister, registerViewModel) { registerViewModel.onRegisterChanged(userNameRegister, userEmailRegister, password = it) }
        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.space_10dp)))
        PasswordTitleText()
        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.space_24dp)))
        AcceptButton(isLoginEnableRegister)
        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.space_40dp)))
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
        text = stringResource(id = R.string.account_title),
        color = BlackGrey,
        fontSize = with(LocalDensity.current){
            dimensionResource(id = R.dimen.font_size_20sp).toSp()
        },
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
        label = { Text(text = stringResource(id = R.string.label_name_title)) },
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
            fontSize = with(LocalDensity.current){
                dimensionResource(id = R.dimen.font_size_16sp).toSp()
            },
        ),
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.box_10dp)),

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
        label = { Text(text = stringResource(id = R.string.label_email_title)) },
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
            fontSize = with(LocalDensity.current){
                dimensionResource(id = R.dimen.font_size_16sp).toSp()
            },
        ),
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.box_10dp))
    )
}

@Composable
fun PasswordRegister(password: String, registerViewModel: RegisterViewModel ,onTextChanged: (String) -> Unit) {
    val passwordVisibility: Boolean by registerViewModel.passwordVisibilityRegister.observeAsState(
        initial = false
    )
    OutlinedTextField(
        value = password,
        onValueChange = { onTextChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        maxLines = 1,
        singleLine = true,
        label = { Text(text = stringResource(id = R.string.label_password_title)) },
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
            fontSize = with(LocalDensity.current){
                dimensionResource(id = R.dimen.font_size_16sp).toSp()
            },
        ),
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.box_10dp)),
        trailingIcon = {
            val icon =
                if (passwordVisibility) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
            IconButton(onClick = { registerViewModel.changePasswordVisibility(passwordVisibility) }) {
                Icon(imageVector = icon, contentDescription = "show password")
            }
        },
        visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation()
    )
}

@Composable
fun PasswordTitleText() {
    Text(
        text = stringResource(id = R.string.password_text_title),
        color = Grey,
        fontSize = with(LocalDensity.current){
            dimensionResource(id = R.dimen.font_size_12sp).toSp()
        },
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
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.box_10dp))
    ) {
        Text(
            text = stringResource(id = R.string.accept_button_text),
            color = White,
            fontSize = with(LocalDensity.current){
                dimensionResource(id = R.dimen.font_size_20sp).toSp()
            },
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
