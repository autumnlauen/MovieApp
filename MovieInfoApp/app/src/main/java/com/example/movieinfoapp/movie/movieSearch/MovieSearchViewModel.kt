package com.example.movieinfoapp.movie.movieSearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieinfoapp.NonNullMutableLiveData
import com.example.movieinfoapp.movie.Movie
import com.example.movieinfoapp.network.API_KEY
import com.example.movieinfoapp.network.MovieApiService
import kotlinx.coroutines.launch

class MovieSearchViewModel : ViewModel() {

    val viewState = NonNullMutableLiveData(
        SearchedMovieViewState(
            movies = emptyList(),
            errorMessage = null
        )
    )

    fun onSearchClicked(query: String) {
        viewModelScope.launch {
            val searchedMovies =
                MovieApiService.retrofitService.getSearchedMovies(query, API_KEY).results
            viewState.value = SearchedMovieViewState(searchedMovies, null)
        }
    }
}

data class SearchedMovieViewState(val movies: List<Movie>, val errorMessage: String?)