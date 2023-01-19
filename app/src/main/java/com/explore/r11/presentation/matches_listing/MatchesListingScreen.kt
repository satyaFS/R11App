package com.explore.r11.presentation.matches_listing

import android.content.ClipData.Item
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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
    if(state.isLoading) {
        Box(Modifier.fillMaxSize(), Alignment.Center){
            CircularProgressIndicator()
        }
    }
    else {
        LazyColumn(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(), contentPadding = PaddingValues(6.dp)
        ) {
            items(state.matches.take(3)) { match ->
                MatchCard(
                    match,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { navController.navigate(Screen.Players.route + "/${match.matchID.toString()}/${match.league}") }
                )
            }
            item { AddMatch(Modifier.fillMaxWidth().height(40.dp).clickable { navController.navigate(Screen.AddMatch.route)  }) }
        }
    }


}
