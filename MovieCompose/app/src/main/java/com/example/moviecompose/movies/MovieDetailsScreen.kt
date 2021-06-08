package com.example.moviecompose.movies

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.moviecompose.services.API_KEY
import com.example.moviecompose.services.IMAGE_BASE_URL
import com.example.moviecompose.services.MovieApiService
import com.google.accompanist.coil.rememberCoilPainter
import kotlinx.coroutines.runBlocking

@Composable
fun MovieDetailsScreen(navController: NavController, movieId: Int) {

    val movie = runBlocking {
        MovieApiService.retrofitService.getClickedMovie(movieId, API_KEY)
    }

    val actorCredits = runBlocking {
        MovieApiService.retrofitService.getMovieCredits(movieId, API_KEY)
    }

    Column {
        Row(Modifier.padding(8.dp)) {
            Image(
                painter = rememberCoilPainter(
                    request = "$IMAGE_BASE_URL${movie.poster_path}"
                ),
                contentDescription = "Movie poster",
                modifier = Modifier.size(width = 150.dp, height = 200.dp)
            )
            Column {
                Text(text = movie.title, fontSize = 32.sp)
                Text(text = movie.releaseDate, fontSize = 22.sp)
                Text(text = movie.runtimeString, fontSize = 22.sp)
            }
        }
        MovieCreditsList(actors = actorCredits, navController = navController)
    }
}

@Composable
fun MovieCreditsList(
    actors: MovieCredits,
    navController: NavController
) {
    LazyColumn {
        items(actors.cast) { actor ->
            ActorRow(actor = actor, navController = navController)
        }
    }
}

@Composable
fun ActorRow(
    actor: ActorForCredits,
    navController: NavController
) {
    Button(
        onClick = {
            navController.navigate("actorDetails/${actor.id}")
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
                    request = "$IMAGE_BASE_URL${actor.profile_path}"
                ),
                contentDescription = "Actor image",
                modifier = Modifier.padding(8.dp)
            )
            Text(
                text = actor.nameString,
                fontSize = 20.sp,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }
}
