package com.example.movieinfoapp.network

import android.util.Log
import com.example.movieinfoapp.actor.actorNetwork.ActorCredits
import com.example.movieinfoapp.actor.actorNetwork.ActorProfile
import com.example.movieinfoapp.actor.actorSearch.SearchedActors
import com.example.movieinfoapp.movie.movieNetwork.MovieCredits
import com.example.movieinfoapp.movie.movieNetwork.MovieProfile
import com.example.movieinfoapp.movie.movieSearch.SearchedMovies
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_URL = "https://api.themoviedb.org/3/"
const val API_KEY = "40c0147b427f3f097af8035867298910"

const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w185/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .client(
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor { message -> Log.d("HTTP", message) }
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
            )
            .build()
    )
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface MovieApi {

    @GET("search/movie")
    suspend fun getSearchedMovies(
        @Query("query") query: String,
        @Query("api_key") apiKey: String
    ): SearchedMovies

    @GET("search/person")
    suspend fun getSearchedActors(
        @Query("query") query: String,
        @Query("api_key") apiKey: String
    ): SearchedActors

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): MovieProfile

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCredits(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): MovieCredits

    @GET("person/{person_id}")
    suspend fun getActorDetails(
        @Path("person_id") personId: Int,
        @Query("api_key") apiKey: String
    ): ActorProfile

    @GET("person/{person_id}/movie_credits")
    suspend fun getActorCredits(
        @Path("person_id") personId: Int,
        @Query("api_key") apiKey: String
    ): ActorCredits
}

object MovieApiService {
    val retrofitService: MovieApi by lazy { retrofit.create(MovieApi::class.java) }
}
