package com.example.moviecompose

import android.os.Bundle
import android.view.Gravity
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.moviecompose.ui.theme.MovieComposeTheme
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val testMovies = listOf(
    Movie("Oceans 8"),
    Movie("John Tucker Must Die"),
    Movie("Clueless"),
    Movie("Oceans 8"),
    Movie("John Tucker Must Die"),
    Movie("Clueless")
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Navigation()
                }
            }
        }
    }
}

data class Movie(
    val title: String
)

@Composable
fun MovieList(
    movies: List<Movie>
) {
    LazyColumn {
        items(movies) { movie ->
            MovieRow(movie)
        }
    }
}

@Composable
fun MovieRow(movie: Movie) {
    Row {
        Image(
            painter = painterResource(id = android.R.drawable.btn_star_big_on),
            contentDescription = "star",
            modifier = Modifier.size(80.dp, 80.dp)
        )
        Text(text = movie.title, fontSize = 20.sp, modifier = Modifier.align(Alignment.CenterVertically))
    }
}
