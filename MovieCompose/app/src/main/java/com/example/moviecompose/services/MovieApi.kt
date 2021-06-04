package com.example.moviecompose.services

import android.util.Log
import com.example.moviecompose.movies.SearchedMovies
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
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
}

object MovieApiService {
    val retrofitService: MovieApi by lazy { retrofit.create(MovieApi::class.java) }
}
