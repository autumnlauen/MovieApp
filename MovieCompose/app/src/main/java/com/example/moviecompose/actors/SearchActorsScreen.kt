package com.example.moviecompose.actors


import android.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.moviecompose.movies.Movie
import com.example.moviecompose.services.API_KEY
import com.example.moviecompose.services.IMAGE_BASE_URL
import com.example.moviecompose.services.MovieApiService
import com.google.accompanist.coil.rememberCoilPainter
import kotlinx.coroutines.launch

@Composable
fun SearchActorsScreen(navController: NavController) {

    val text = remember { mutableStateOf(TextFieldValue()) }

    val listOfActors = remember { mutableStateOf(emptyList<Actor>()) }

    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = text.value,
            leadingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.ic_menu_search),
                    contentDescription = "Search"
                )
            },
            onValueChange = { text.value = it },
            label = { Text(text = "Search by actor") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            onClick = {
                coroutineScope.launch {
                    listOfActors.value =
                        MovieApiService.retrofitService.getSearchedActors(
                            text.value.text,
                            API_KEY
                        ).results
                }
            }
        ) {
            Text(text = "Search", fontSize = 20.sp)
        }
        ActorList(actors = listOfActors.value, navController = navController)
    }
}

@Composable
fun ActorList(
    actors: List<Actor>?,
    navController: NavController
) {
    if (actors == null) {
        Column {
        }
    } else {
        LazyColumn {
            items(actors) { actor ->
                MovieRow(actor, navController)
            }
        }
    }
}

@Composable
fun MovieRow(actor: Actor, navController: NavController) {
    Button(
        onClick = {
            navController.navigate("actorDetails/${actor.id}")
        },
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(elevation = 1.dp)
        ) {
            Image(
                painter = rememberCoilPainter(
                    request = "$IMAGE_BASE_URL${actor.profile_path}"
                ),
                contentDescription = "Movie image",
                modifier = Modifier.padding(8.dp)
            )
            Text(
                text = actor.name,
                fontSize = 20.sp,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }
}
