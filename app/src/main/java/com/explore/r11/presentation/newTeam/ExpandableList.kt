package com.explore.r11.presentation.newTeam

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.explore.r11.domain.model.NewPlayer

@Composable
fun ExpandableList(
    players: List<NewPlayer>,
    teamName: String,
    teamNo: Int,
    modifier: Modifier,
    listHeight: Float,
    addPlayer: () -> Unit,
    updateTeamName:(Int,String)->Unit,
    updatePlayerName: (NewPlayer, String) -> Unit,
    updatePlayerSalary:(NewPlayer,Double)->Unit,
    updatePlayerType:(NewPlayer,String)->Unit
){
    Column(modifier = modifier) {
        var editable by remember { mutableStateOf(false) }
        TeamTitleHeader(teamName = teamName, teamNo =teamNo ,updateTeamName=updateTeamName) { editable = !editable }
        AnimatedVisibility(visible = editable) {
         Column(Modifier.wrapContentSize()) {
             Button(
                 onClick = { addPlayer()},
                 colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                 modifier = Modifier
                     .fillMaxWidth()
                     .wrapContentHeight()
             ) {
                 Icon(Icons.Default.Add, contentDescription = "")
                 Text(text = "Player")
             }
             LazyColumn(Modifier.fillMaxHeight(listHeight) ){

                 items( players){player->
                     NewPlayerItem(player,updatePlayerName, updatePlayerSalary,updatePlayerType)
                 }
             }
         }
         }

    }
}