package com.explore.r11.presentation.matches_listing

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

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
                    .clickable { }
            )
        }

    }
}
