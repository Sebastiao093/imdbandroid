package com.example.imdb


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.imdb.model.Movie
import com.example.imdb.ui.theme.*

@Composable
fun SearchScreen(navController: NavHostController) {
    Column(
        Modifier
            .fillMaxSize()
            .background(color = White),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        BodySearch(Modifier.align(Alignment.CenterHorizontally), navController)
        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.space_small)))
        MoviesListRecyclerView(Modifier.align(Alignment.CenterHorizontally))
    }

}

@Composable
fun BodySearch(modifier: Modifier, navController: NavHostController) {
    var searchText by remember { mutableStateOf("") }
    Column(modifier = modifier) {
        Box(
            Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.box_small))
                .background(color = White100)
        ) {
            SearchField(searchText) { searchText = it }
        }
    }

}

@Composable
fun SearchField(searchText: String, onTextChanged: (String) -> Unit) {
    Box(Modifier.padding(dimensionResource(id = R.dimen.padding_large))) {
        TextField(
            value = searchText,
            onValueChange = { onTextChanged(it) },
            modifier = Modifier.fillMaxWidth(),
            maxLines = 1,
            singleLine = true,
            label = {
                Text(
                    text = stringResource(id = R.string.searchfield_text),
                    color = Grey,
                    fontFamily = RobotoBoldFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = with(LocalDensity.current) {
                        dimensionResource(id = R.dimen.fontsize_MMmedium).toSp()
                    },
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            colors = TextFieldDefaults.textFieldColors(
                textColor = Grey,
                backgroundColor = White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Grey,
                focusedLabelColor = Grey
            ),
            textStyle = TextStyle(
                fontFamily = RobotoBoldFamily,
                fontWeight = FontWeight.Medium,
                fontSize = with(LocalDensity.current) {
                    dimensionResource(id = R.dimen.fontsize_MMmedium).toSp()
                },
                color = Grey
            ),
            shape = RoundedCornerShape(dimensionResource(id = R.dimen.box_default)),
            leadingIcon = {
                IconButton(onClick = { }) {
                    Icon(imageVector = Icons.Filled.Search, contentDescription = "search")
                }
            },
        )
    }
}

@Composable
fun MoviesListRecyclerView(modifier: Modifier) {
    LazyColumn(

        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        items(getMovies()) { movie ->
            CardItemMovie(movie = movie, modifier)
        }
    }
}

@Composable
fun CardItemMovie(movie: Movie, modifier: Modifier) {
    Box(modifier = modifier) {
        val configuration = LocalConfiguration.current
        val screenWidth = configuration.screenWidthDp
        Divider(
            color = Grey,
            thickness = dimensionResource(id = R.dimen.divider_thickness).value.dp,
            modifier = Modifier
                .width((screenWidth * dimensionResource(id = R.dimen.percent).value).dp)
                .align(
                    Alignment.Center
                ),
        )
    }
    Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.space_small)))
    Row {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.box_Mmediummore)),
            backgroundColor = White,
            elevation = 0.dp
        ) {
            Row(horizontalArrangement = Arrangement.Center) {
                Column() {
                    Image(
                        painter = painterResource(id = movie.photo),
                        contentDescription = "Movie Photo",
                        modifier = Modifier
                            .height(dimensionResource(id = R.dimen.box_Mmediummore))
                            .width(dimensionResource(id = R.dimen.box_medium))
                            .padding(
                                top = dimensionResource(id = R.dimen.padding_small),
                                bottom = dimensionResource(id = R.dimen.padding_small),
                                start = dimensionResource(id = R.dimen.padding_small)
                            ),
                        contentScale = ContentScale.Crop
                    )
                }
                Column(
                    verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.space_XXsmall)),
                    modifier = Modifier
                        .width(dimensionResource(id = R.dimen.box_MMMmedium))
                        .height(dimensionResource(id = R.dimen.box_Mmediummore))
                        .padding(
                            top = dimensionResource(id = R.dimen.padding_MMmedium),
                            bottom = dimensionResource(id = R.dimen.padding_small),
                            start = dimensionResource(id = R.dimen.padding_XXsmall)
                        ),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = movie.name,
                        color = Black,
                        fontFamily = RobotoBoldFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = with(LocalDensity.current) {
                            dimensionResource(id = R.dimen.fontsize_MMmedium).toSp()
                        },
                    )
                    Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.space_default)))
                    Text(
                        text = "${movie.year}",
                        color = Grey,
                        fontFamily = RobotoBoldFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = with(LocalDensity.current) {
                            dimensionResource(id = R.dimen.fontsize_MMmedium).toSp()
                        },
                    )
                    Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.space_MMmedium)))
                    Text(
                        text = movie.actors,
                        color = Grey,
                        fontFamily = RobotoBoldFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = with(LocalDensity.current) {
                            dimensionResource(id = R.dimen.fontsize_Mmedium).toSp()
                        },
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

        }
    }

}

fun getMovies(): List<Movie> {
    return listOf(
        Movie("Dune", 2021, "Timothee Chalamet, Zendaya", R.drawable.dune),
        Movie("Free Guy", 2021, "Ryan Reynolds, Jodie Comer", R.drawable.freeguy),
        Movie("Shang-Chi", 2021, "Simu Lui, Awkwafina", R.drawable.shangchi),
        Movie("Suicide Squad", 2021, "Margot Robbie, John Cena", R.drawable.suicidesquad)
    )
}

