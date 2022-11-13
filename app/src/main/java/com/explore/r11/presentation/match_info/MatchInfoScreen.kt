package com.explore.r11.presentation.match_info

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun MatchInfoScreen(
    viewModel: MatchInfoViewModel = hiltViewModel(),
    navController:NavController
){
    LazyColumn(Modifier.fillMaxSize()) {
        items(viewModel.state.selectedPlayers){
            PlayerItem(player = it, viewModel.state.matchId)
        }
    }
}