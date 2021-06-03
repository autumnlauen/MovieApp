package com.example.moviecompose.searchmovies

import android.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
@Preview
fun SearchMoviesScreen() {

    val text = remember { mutableStateOf(TextFieldValue()) }

    val listOfMovies = remember { mutableStateOf(emptyList<Movie>())}

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
            onClick = { listOfMovies.value = testMovies }) {
            Text(text = "Search", fontSize = 20.sp)
        }
        MovieList(movies = listOfMovies.value)
    }
}

val testMovies = listOf(
    Movie("Oceans 8"),
    Movie("John Tucker Must Die"),
    Movie("Clueless"),
    Movie("Oceans 8"),
    Movie("John Tucker Must Die"),
    Movie("Clueless")
)

data class Movie(
    val title: String
)

@Composable
fun MovieList(
    movies: List<Movie>?
) {
    if (movies == null) {
        Column() {
        }
    } else {
        LazyColumn {
            items(movies) { movie ->
                MovieRow(movie)
            }
        }
    }
}

@Composable
fun MovieRow(movie: Movie) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = painterResource(id = android.R.drawable.btn_star_big_on),
            contentDescription = "star",
            modifier = Modifier.size(80.dp, 80.dp)
        )
        Text(
            text = movie.title,
            fontSize = 20.sp,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}
