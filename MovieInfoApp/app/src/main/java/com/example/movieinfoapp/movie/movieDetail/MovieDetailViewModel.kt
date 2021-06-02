package com.example.movieinfoapp.movie.movieDetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieinfoapp.network.*
import com.example.movieinfoapp.movie.movieNetwork.ActorProfileForMovieCredits
import com.example.movieinfoapp.movie.movieNetwork.MovieProfile
import kotlinx.coroutines.launch

class MovieDetailViewModel : ViewModel() {

    val viewState = MutableLiveData<MovieDetailViewState>()

    fun setMovieId (movieId: Int) {
        viewModelScope.launch {
            val movie = MovieApiService.retrofitService.getMovieDetails(movieId, API_KEY)
            val actors = MovieApiService.retrofitService.getMovieCredits(movieId, API_KEY).cast
            viewState.value = MovieDetailViewState(movie,actors)
        }
    }
}

data class MovieDetailViewState(val movie: MovieProfile, val actors: List<ActorProfileForMovieCredits>)