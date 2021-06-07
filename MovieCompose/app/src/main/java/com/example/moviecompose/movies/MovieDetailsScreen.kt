package com.example.moviecompose.movies

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.moviecompose.services.API_KEY
import com.example.moviecompose.services.MovieApiService
import kotlinx.coroutines.runBlocking

@Composable
fun MovieDetailsScreen(movieId: Int) {

    /*Column {
        Row {
            Image(painter = , contentDescription =)
            Column {
                Text(text =)
                Text(text =)
                Text(text =)
            }
        }
    }*/
    
    val movie = runBlocking {
        MovieApiService.retrofitService.getClickedMovie(movieId, API_KEY)
    }

    Text(text = movie.title)
}
