package com.explore.r11.presentation.newTeam

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun NewMatchScreen(viewModel: NewTeamViewModel = hiltViewModel(),navController: NavController ){
    val state = viewModel.state
    Column(Modifier.fillMaxSize().padding(top = 5.dp)) {
        ExpandableList(players = state.teamTwoPlayers, teamName = state.teamTwoName )
        Divider(Modifier.size(2.dp))
        ExpandableList(players = state.teamOnePlayers, teamName = state.teamOneName )
    }

}
