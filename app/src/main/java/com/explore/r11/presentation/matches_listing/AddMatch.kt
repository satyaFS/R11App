package com.explore.r11.presentation.matches_listing

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun AddMatch(modifier: Modifier){
    Card(modifier = modifier
        //.padding(bottom = 15.dp)
//        .fillMaxWidth()
//        .height(40.dp)
        , elevation = 5.dp

    ) {
        Box(Modifier.fillMaxSize(), Alignment.CenterEnd) {
//            Text(text = "New", Modifier.fillMaxSize().padding(end = 100.dp, top = 5.dp),textAlign = TextAlign.Center)
            Icon(Icons.Outlined.Add, modifier = Modifier.fillMaxSize(),contentDescription = "Add new match" )
//            Text(text = "Match", Modifier.fillMaxSize().padding(start = 100.dp, top = 5.dp),textAlign = TextAlign.Center)
        }
    }
}