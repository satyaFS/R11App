package com.explore.r11.presentation.newTeam

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun NewMatchScreen(viewModel: NewTeamViewModel = hiltViewModel(),navController: NavController ){
    val state = viewModel.state
    val focusManager = LocalFocusManager.current
    Column(
        Modifier
            .fillMaxSize()
            .padding(top = 5.dp)
            .clickable(indication = null,
                interactionSource = remember { MutableInteractionSource() }) {
                focusManager.clearFocus()
            }
    ) {
        ExpandableList(players = state.teamOnePlayers, teamName = state.teamOneName, Modifier.wrapContentHeight(),0.45f,  addPlayer = {viewModel.addPlayer(1 , state.teamTwoName)} )
        Divider(Modifier.size(2.dp))
        ExpandableList(players = state.teamTwoPlayers, teamName = state.teamTwoName, Modifier.wrapContentHeight(), 1f) {
            viewModel.addPlayer(2,
                state.teamOneName)
        }
    }

}
