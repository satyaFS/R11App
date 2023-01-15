package com.explore.r11.presentation.newTeam

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.explore.r11.domain.model.NewPlayer
import com.explore.r11.domain.model.Player

@Composable
fun ExpandableList(players:List<NewPlayer>, teamName:String, modifier: Modifier, listHeight:Float, addPlayer:()->Unit){
    Column(modifier = modifier) {
        var editable by remember { mutableStateOf(false) }
        TeamTitleHeader(teamName = teamName) { editable = !editable }
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

                 items( players){ it
                     NewPlayerItem(name = it.name, salary = it.salary.toString())
                 }
             }
         }
         }

    }
}