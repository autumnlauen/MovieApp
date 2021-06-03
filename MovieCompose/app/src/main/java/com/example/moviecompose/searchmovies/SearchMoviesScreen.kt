package com.example.moviecompose.searchmovies

import android.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun SearchMoviesScreen() {

    val text = remember { mutableStateOf(TextFieldValue()) }

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
                    contentDescription = "search"
                )
            },
            onValueChange = { text.value = it },
            label = { Text(text = "Search by movie") })
    }
}
