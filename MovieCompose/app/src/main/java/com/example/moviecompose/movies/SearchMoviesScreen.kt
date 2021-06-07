package com.example.moviecompose.movies

import android.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.moviecompose.services.API_KEY
import com.example.moviecompose.services.IMAGE_BASE_URL
import com.example.moviecompose.services.MovieApiService
import com.google.accompanist.coil.rememberCoilPainter
import kotlinx.coroutines.launch

@Composable
fun SearchMoviesScreen(navController: NavController) {

    val text = remember { mutableStateOf(TextFieldValue()) }

    val listOfMovies = remember { mutableStateOf(emptyList<Movie>()) }

    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = text.value,
            leadingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.ic_menu_search),
                    contentDescription = "search"
                )
            },
            onValueChange = { text.value = it },
            label = { Text(text = "Search by movie") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            onClick = {
                coroutineScope.launch {
                    listOfMovies.value =
                        MovieApiService.retrofitService.getSearchedMovies(
                            text.value.text,
                            API_KEY
                        ).results
                }
            }
        ) {
            Text(text = "Search", fontSize = 20.sp)
        }
        MovieList(movies = listOfMovies.value, navController = navController)
    }
}

@Composable
fun MovieList(
    movies: List<Movie>?,
    navController: NavController
) {
    if (movies == null) {
        Column {
        }
    } else {
        LazyColumn {
            items(movies) { movie ->
                MovieRow(movie, navController)
            }
        }
    }
}

@Composable
fun MovieRow(movie: Movie, navController: NavController) {
    Button(
        onClick = {
            navController.navigate("movieDetails/${movie.id}")
        },
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(elevation = 1.dp)
        ) {
            Image(
                painter = rememberCoilPainter(
                    request = "$IMAGE_BASE_URL${movie.poster_path}"
                ),
                contentDescription = "Movie image",
                modifier = Modifier.padding(8.dp)
            )
            Text(
                text = movie.title,
                fontSize = 20.sp,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }
}
