package com.example.moviecompose

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.moviecompose.choose.ChooseSearchComposable
import com.example.moviecompose.searchactors.SearchActorsScreen
import com.example.moviecompose.searchmovies.SearchMoviesScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "choose") {
        composable("choose") {
            ChooseSearchComposable(navController = navController)
        }
        composable("searchMovies") {
            SearchMoviesScreen()
        }
        composable("searchActors") {
            SearchActorsScreen()
        }
    }
}
