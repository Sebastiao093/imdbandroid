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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.imdb.model.MovieResult
import com.example.imdb.search.data.network.SearchService
import com.example.imdb.search.ui.SearchViewModel
import com.example.imdb.ui.theme.*

@Composable
fun SearchScreen(navController: NavHostController, searchViewModel: SearchViewModel) {
    searchViewModel.getTopRatedMovies()
    Column(
        Modifier
            .fillMaxSize()
            .background(color = White),
        verticalArrangement = Arrangement.Top
    ) {
        BodySearch(Modifier.align(Alignment.CenterHorizontally), navController)
        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.space_10dp)))
        MoviesListRecyclerView(Modifier.align(Alignment.CenterHorizontally), searchViewModel)
    }

}

@Composable
fun BodySearch(modifier: Modifier, navController: NavHostController) {
    var searchText by remember { mutableStateOf("") }
    Column(modifier = modifier) {
        Box(
            Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.box_100dp))
                .background(color = White100)
        ) {
            SearchField(searchText) { searchText = it }
        }
    }

}

@Composable
fun SearchField(searchText: String, onTextChanged: (String) -> Unit) {
    Box(Modifier.padding(dimensionResource(id = R.dimen.padding_24dp))) {
        TextField(
            value = searchText,
            onValueChange = { onTextChanged(it) },
            modifier = Modifier.fillMaxWidth(),
            maxLines = 1,
            singleLine = true,
            label = {
                Text(
                    text = stringResource(id = R.string.search_field_text),
                    color = Grey,
                    fontFamily = RobotoBoldFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = with(LocalDensity.current) {
                        dimensionResource(id = R.dimen.font_size_16sp).toSp()
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
                    dimensionResource(id = R.dimen.font_size_16sp).toSp()
                },
                color = Grey
            ),
            shape = RoundedCornerShape(dimensionResource(id = R.dimen.box_10dp)),
            leadingIcon = {
                IconButton(onClick = { }) {
                    Icon(imageVector = Icons.Filled.Search, contentDescription = "search")
                }
            },
        )
    }
}

@Composable
fun MoviesListRecyclerView(modifier: Modifier, searchViewModel: SearchViewModel) {
    val movieList: List<MovieResult> by searchViewModel.movieList.observeAsState(initial = listOf())
    LazyColumn(

        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        items(movieList) { movieResult ->
            CardItemMovie(movie = movieResult, modifier)
        }
    }
}

@Composable
fun CardItemMovie(movie: MovieResult, modifier: Modifier) {
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
    Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.space_10dp)))
    Row {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.box_180dp)),
            backgroundColor = White,
            elevation = 0.dp
        ) {
            Row(horizontalArrangement = Arrangement.Center) {
                Column {
                    Image(
                        painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w200${movie.posterPath}"),
                        contentDescription = "Movie Photo",
                        modifier = Modifier
                            .height(dimensionResource(id = R.dimen.box_180dp))
                            .width(dimensionResource(id = R.dimen.box_120dp))
                            .padding(
                                top = dimensionResource(id = R.dimen.padding_10dp),
                                bottom = dimensionResource(id = R.dimen.padding_10dp),
                                start = dimensionResource(id = R.dimen.padding_10dp)
                            ),
                        contentScale = ContentScale.Crop
                    )
                }
                Column(
                    verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.space_6dp)),
                    modifier = Modifier
                        .width(dimensionResource(id = R.dimen.box_200dp))
                        .height(dimensionResource(id = R.dimen.box_150dp))
                        .padding(
                            top = dimensionResource(id = R.dimen.padding_16dp),
                            bottom = dimensionResource(id = R.dimen.padding_10dp),
                            start = dimensionResource(id = R.dimen.padding_6dp)
                        ),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = movie.title,
                        color = Black,
                        fontFamily = RobotoBoldFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = with(LocalDensity.current) {
                            dimensionResource(id = R.dimen.font_size_16sp).toSp()
                        },
                    )
                    Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.space_2dp)))
                    Text(
                        text = movie.releaseDate.substring(startIndex = 0, endIndex = 4),
                        color = Grey,
                        fontFamily = RobotoBoldFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = with(LocalDensity.current) {
                            dimensionResource(id = R.dimen.font_size_16sp).toSp()
                        },
                    )
                    Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.space_16dp)))
                    Text(
                        text = movie.overview,
                        color = Grey,
                        fontFamily = RobotoBoldFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = with(LocalDensity.current) {
                            dimensionResource(id = R.dimen.font_size_14sp).toSp()
                        },
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

        }
    }

}

suspend fun getTopRatedMovies(apiKey: String): List<MovieResult> {
    return SearchService().getTopRatedMovies(apiKey)
}

fun <T> SnapshotStateList<T>.swapList(newList: List<T>){
    clear()
    addAll(newList)
}

