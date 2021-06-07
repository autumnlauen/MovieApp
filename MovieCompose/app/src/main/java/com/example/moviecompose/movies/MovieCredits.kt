package com.example.moviecompose.movies

data class ActorForCredits(
    val id: Int,
    val name: String,
    val profile_path: String?,
    val character: String?
) {
    val nameString: String
        get() {
            return if (character != null) {
                "$name ($character)"
            } else {
                name
            }
        }
}

data class MovieCredits(val id: Int, val cast: List<ActorForCredits>)
