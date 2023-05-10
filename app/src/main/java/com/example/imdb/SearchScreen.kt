package com.example.imdb

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
        Spacer(modifier = Modifier.size(10.dp))
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
                .height(100.dp)
                .background(color = White100)
        ) {
            SearchField(searchText) { searchText = it }
        }
    }

}

@Composable
fun SearchField(searchText: String, onTextChanged: (String) -> Unit) {
    Box(Modifier.padding(23.dp)) {
        TextField(
            value = searchText,
            onValueChange = { onTextChanged(it) },
            modifier = Modifier.fillMaxWidth(),
            maxLines = 1,
            singleLine = true,
            label = {
                Text(
                    text = "Buscar IMDb",
                    color = Grey,
                    fontFamily = RobotoBoldFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
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
                fontSize = 16.sp,
                color = Grey
            ),
            shape = RoundedCornerShape(10.dp),
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
        Divider(
            color = Grey,
            thickness = 0.5.dp,
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .align(
                    Alignment.Center
                ),
        )
    }
    Spacer(modifier = Modifier.size(10.dp))
    Row {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            backgroundColor = White,
            elevation = 0.dp
        ) {
            Row(horizontalArrangement = Arrangement.Center) {
                Column() {
                    Image(
                        painter = painterResource(id = movie.photo),
                        contentDescription = "Movie Photo",
                        modifier = Modifier
                            .height(150.dp)
                            .width(120.dp)
                            .padding(top = 10.dp, bottom = 10.dp, start = 10.dp),
                        contentScale = ContentScale.Crop
                    )
                }
                Column(verticalArrangement = Arrangement.spacedBy(5.dp), modifier = Modifier.width(200.dp).height(150.dp).padding(top = 15.dp, bottom = 10.dp, start = 5.dp), horizontalAlignment = Alignment.Start) {
                    Text(
                        text = movie.name,
                        color = Black,
                        fontFamily = RobotoBoldFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp,
                    )
                    Spacer(modifier = Modifier.size(2.dp))
                    Text(
                        text = "${movie.year}",
                        color = Grey,
                        fontFamily = RobotoBoldFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp,
                    )
                    Spacer(modifier = Modifier.size(15.dp))
                    Text(
                        text = movie.actors,
                        color = Grey,
                        fontFamily = RobotoBoldFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp,
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

