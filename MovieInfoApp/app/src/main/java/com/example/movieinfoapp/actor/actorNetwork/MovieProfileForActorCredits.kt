package com.example.movieinfoapp.actor.actorNetwork

data class MovieProfileForActorCredits(val id: Int, val title: String, val poster_path: String?, val release_date: String?) {
    val titleString: String
        get() {
            return if (release_date != null && release_date.length >= 4){
                "$title (${release_date.substring(0,4)})"
            } else {
                title
            }
        }
}