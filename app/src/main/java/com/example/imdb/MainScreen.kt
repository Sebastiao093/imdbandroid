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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
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
            .padding(dimensionResource(id = R.dimen.padding_extralarge))
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
        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.space_MMmedium)))
        UserTitle()
        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.space_XXsmall)))
        UserEmail(userEmail) { userEmail = it }
        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.space_small)))
        PasswordTitle()
        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.space_XXsmall)))
        UserPassword(userPassword) { userPassword = it }
        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.space_XXsmall)))
        ForgotPasswordTitle()
        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.space_MMmedium)))
        LoginButton(isLoginEnable)
        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.space_extralarge)))
        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.space_XXsmall)))
        TextTitle(Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.space_MMmedium)))
        SocialIcons()
        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.space_MMmedium)))
        AccountText(Modifier.align(Alignment.CenterHorizontally), navController)
        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.space_MMmedium)))
        InvitedText(Modifier.align(Alignment.CenterHorizontally), navController)
    }

}

@Composable
fun HeaderTitle(modifier: Modifier) {
    Image(painter = painterResource(id = R.drawable.imdb_logo2x), contentDescription = "logo", modifier = modifier)
}

@Composable
fun UserTitle() {
    Text(
        text = stringResource(id = R.string.user_title),
        color = BlackGrey,
        fontSize = with(LocalDensity.current){
            dimensionResource(id = R.dimen.fontsize_MMmedium).toSp()
        },
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
            backgroundColor = White100,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = Grey70,
        ),
        textStyle = TextStyle(
            fontFamily = RobotoBoldFamily,
            fontWeight = FontWeight.Light,
            fontSize = with(LocalDensity.current){
                dimensionResource(id = R.dimen.fontsize_Mmedium).toSp()
            },
        ),
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.box_default))
    )
}

@Composable
fun PasswordTitle() {
    Text(
        text = stringResource(id = R.string.password_title),
        color = BlackGrey,
        fontSize = with(LocalDensity.current){
            dimensionResource(id = R.dimen.fontsize_MMmedium).toSp()
        },
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
            backgroundColor = White100,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = Grey70,
        ),
        textStyle = TextStyle(
            fontFamily = RobotoBoldFamily,
            fontWeight = FontWeight.Light,
            fontSize = with(LocalDensity.current){
                dimensionResource(id = R.dimen.fontsize_Mmedium).toSp()
            },
        ),
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.box_default)),
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
        text = stringResource(id = R.string.forgotpassword_title),
        color = BlackGrey,
        fontSize = with(LocalDensity.current){
            dimensionResource(id = R.dimen.fontsize_medium).toSp()
        },
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
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.box_default))
    ) {
        Text(
            text = stringResource(id = R.string.loginbutton_title),
            color = White,
            fontSize = with(LocalDensity.current){
                dimensionResource(id = R.dimen.fontsize_MMMmedium).toSp()
            },
            fontFamily = RobotoBoldFamily,
            fontWeight = FontWeight.Bold
        )

    }

}

@Composable
fun TextTitle(modifier: Modifier) {
    Text(
        text = stringResource(id = R.string.text_title),
        color = Grey,
        fontSize = with(LocalDensity.current){
            dimensionResource(id = R.dimen.fontsize_MMmedium).toSp()
        },
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
                .size(dimensionResource(id = R.dimen.box_XXXsmall))
                .clip(CircleShape)
                .background(color = White100),
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
        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.space_small)))
        Box(
            modifier = Modifier
                .size(dimensionResource(id = R.dimen.box_XXXsmall))
                .clip(CircleShape)
                .background(color = White100),
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
        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.space_small)))
        Box(
            modifier = Modifier
                .size(dimensionResource(id = R.dimen.box_XXXsmall))
                .clip(CircleShape)
                .background(color = White100),
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
            text = stringResource(id = R.string.account_text),
            color = Grey,
            fontSize = with(LocalDensity.current){
                dimensionResource(id = R.dimen.fontsize_MMmedium).toSp()
            },
            fontFamily = RobotoBoldFamily,
            fontWeight = FontWeight.Light,
            modifier = modifier
        )
        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.space_XXsmall)))
        Text(
            text = stringResource(id = R.string.accountregister_text),
            color = BlackGrey,
            fontSize = with(LocalDensity.current){
                dimensionResource(id = R.dimen.fontsize_MMmedium).toSp()
            },
            fontFamily = RobotoBoldFamily,
            fontWeight = FontWeight.Light,
            modifier = modifier.clickable { navController.navigate(AppScreens.RegisterScreen.route) },

            )

    }
}


@Composable
fun InvitedText(modifier: Modifier, navController: NavHostController) {
    Text(
        text = stringResource(id = R.string.invited_text),
        color = BlackGrey,
        fontSize = with(LocalDensity.current){
            dimensionResource(id = R.dimen.fontsize_MMmedium).toSp()
        },
        fontFamily = RobotoBoldFamily,
        fontWeight = FontWeight.Medium,
        modifier = modifier.clickable { navController.navigate(AppScreens.SearchScreen.route) }
    )
}


/*
@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    Body()
}*/
