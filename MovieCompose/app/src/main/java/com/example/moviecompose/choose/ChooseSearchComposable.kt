package com.example.moviecompose.choose

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun ChooseSearchComposable(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "What would you like to search?",
            fontSize = 20.sp,
            modifier = Modifier.padding(16.dp)
        )
        Button(
            onClick = { navController.navigate("searchMovies") },
            modifier = Modifier
                .padding(16.dp)
                .size(200.dp, 200.dp)
        ) {
            Text(text = "Movies", fontSize = 20.sp)
        }
        Button(
            onClick = { navController.navigate("searchActors") },
            modifier = Modifier
                .padding(16.dp)
                .size(200.dp, 200.dp)
        ) {
            Text(text = "Actors", fontSize = 20.sp)
        }
    }
}
