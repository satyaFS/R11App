package com.explore.r11.presentation.newTeam

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
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
        ExpandableList(
            players = state.teamOnePlayers,
            teamNo = 1,
            teamName = state.teamOneName,
            modifier = Modifier.wrapContentHeight(),
            listHeight = 0.45f,
            addPlayer = { viewModel.addPlayer(1) },
            updateTeamName = viewModel::updateTeamName,
            updatePlayerName = viewModel::updatePlayerName,
            updatePlayerSalary = viewModel::updatePlayerSalary,
            updatePlayerType = viewModel::updatePlayerType
        )
        Divider(Modifier.size(2.dp))
        ExpandableList(
            players = state.teamTwoPlayers,
            teamNo = 2,
            teamName = state.teamTwoName,
            modifier = Modifier.wrapContentHeight(),
            listHeight = 0.8f,
            addPlayer = { viewModel.addPlayer(2) },
            updateTeamName = viewModel::updateTeamName,
            updatePlayerName = viewModel::updatePlayerName,
            updatePlayerSalary = viewModel::updatePlayerSalary,
            updatePlayerType = viewModel::updatePlayerType
        )
        Box(modifier = Modifier
            .fillMaxSize()
            ,contentAlignment = Alignment.BottomCenter) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Button(onClick = { viewModel.saveMatchInfo() }) {
                    Text(text = "Save")
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(Icons.Rounded.Home, contentDescription = "",tint = Color(0xFFB0BEC5))
                }
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Generate Teams")
                }
            }
        }
    }

}

@Composable
@Preview
fun check(){
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Save")
        }
        IconButton(onClick = { /*TODO*/ }) {
            Icon(Icons.Rounded.Home, contentDescription = "")
        }
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Generate Teams")
        }
    }
}