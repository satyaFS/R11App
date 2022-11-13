package com.explore.r11.presentation.matches_listing

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.explore.r11.presentation.navigation.Screen

@Composable
fun MatchesListing(
    navController: NavController,
    viewModel:MatchesListingViewModel = hiltViewModel()
){
    val state = viewModel.state
    LazyColumn(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        ,contentPadding = PaddingValues(6.dp)
    ){
        items(state.matches){match->
            MatchCard(
                match,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {navController.navigate(Screen.Players.route+"/${match.matchID.toString()}") }
            )
        }

    }

    if(state.isLoading) {
        Box(Modifier.fillMaxSize(), Alignment.Center){
            CircularProgressIndicator()
        }
    }
}
