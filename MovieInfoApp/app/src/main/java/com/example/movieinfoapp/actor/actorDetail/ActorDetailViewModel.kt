package com.example.movieinfoapp.actor.actorDetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieinfoapp.network.API_KEY
import com.example.movieinfoapp.actor.actorNetwork.ActorProfile
import com.example.movieinfoapp.actor.actorNetwork.MovieProfileForActorCredits
import com.example.movieinfoapp.network.MovieApiService
import kotlinx.coroutines.launch

class ActorDetailViewModel : ViewModel() {

    val viewState = MutableLiveData<ActorDetailViewState>()

    fun setActorId (actorId: Int) {
        viewModelScope.launch {
            val actor = MovieApiService.retrofitService.getActorDetails(actorId, API_KEY)
            val movies = MovieApiService.retrofitService.getActorCredits(actorId, API_KEY).cast
            viewState.value = ActorDetailViewState(actor,movies)
        }
    }
}

data class ActorDetailViewState(val actor: ActorProfile, val movies: List<MovieProfileForActorCredits>)