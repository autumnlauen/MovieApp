package com.example.movieinfoapp.actor.actorSearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieinfoapp.NonNullMutableLiveData
import com.example.movieinfoapp.actor.Actor
import com.example.movieinfoapp.network.API_KEY
import com.example.movieinfoapp.network.MovieApiService
import kotlinx.coroutines.launch

class ActorSearchViewModel : ViewModel() {
    val viewState = NonNullMutableLiveData(
        SearchedActorViewState(
            actors = emptyList(),
            errorMessage = null
        )
    )

    fun onSearchClicked(query: String) {
        viewModelScope.launch {
            val searchedActors =
                MovieApiService.retrofitService.getSearchedActors(query, API_KEY).results
            viewState.value = SearchedActorViewState(searchedActors, null)
        }
    }
}

data class SearchedActorViewState(val actors: List<Actor>, val errorMessage: String?)
