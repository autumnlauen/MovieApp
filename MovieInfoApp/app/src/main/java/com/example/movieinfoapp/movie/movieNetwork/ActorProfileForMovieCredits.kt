package com.example.movieinfoapp.movie.movieNetwork

data class ActorProfileForMovieCredits(val id: Int, val name: String, val profile_path: String?, val character: String?) {
    val nameString: String
        get() {
            return if (character != null) {
                "$name ($character)"
            } else {
                name
            }
        }
}