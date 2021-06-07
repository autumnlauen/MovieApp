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
import com.example.moviecompose.actors.ActorCredits
import com.example.moviecompose.actors.MovieForCredits
import com.example.moviecompose.services.API_KEY
import com.example.moviecompose.services.IMAGE_BASE_URL
import com.example.moviecompose.services.MovieApiService
import com.google.accompanist.coil.rememberCoilPainter
import kotlinx.coroutines.runBlocking

@Composable
fun ActorDetailsScreen(navController: NavController, actorId: Int) {

    val actor = runBlocking {
        MovieApiService.retrofitService.getClickedActor(actorId, API_KEY)
    }

    val actorCredits = runBlocking {
        MovieApiService.retrofitService.getActorCredits(actorId, API_KEY)
    }

    Column {
        Row(Modifier.padding(8.dp)) {
            Image(
                painter = rememberCoilPainter(
                    request = "$IMAGE_BASE_URL${actor.profile_path}"
                ),
                contentDescription = "Actor picture",
                modifier = Modifier.size(width = 150.dp, height = 200.dp)
            )
            Column {
                Text(text = actor.name, fontSize = 32.sp)
                Text(text = actor.age, fontSize = 22.sp)
                Text(text = actor.placeOfBirth, fontSize = 22.sp)
            }
        }
        ActorCreditsList(movies = actorCredits, navController = navController)
    }
}

@Composable
fun ActorCreditsList(
    movies: ActorCredits,
    navController: NavController
) {
    LazyColumn {
        items(movies.cast) { movie ->
            MovieRow(movie = movie, navController = navController)
        }
    }
}

@Composable
fun MovieRow(
    movie: MovieForCredits,
    navController: NavController
) {
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
                text = movie.titleString,
                fontSize = 20.sp,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }
}
