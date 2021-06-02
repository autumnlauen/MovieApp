package com.example.movieinfoapp.movie.movieNetwork

data class MovieProfile (val id: Int,
                         val poster_path: String?,
                         val title: String?,
                         val release_date: String?,
                         val runtime: Int?) {
    val releaseDate: String
        get() {
            return if (release_date != null) {
                "Released: ${release_date.substring(0,4)}"
            } else {
                "Release date not available"
            }
        }

    val runtimeString: String
        get() {
            return if (runtime != null) {
                "Runtime: $runtime mins"
            } else {
                "Runtime not available"
            }
        }
}
