package com.explore.r11.presentation.newTeam

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.explore.r11.domain.model.Player

@Composable
fun ExpandableList(players:List<Player>, teamName:String, modifier: Modifier, listHeight:Float){
    Column(modifier = modifier) {
        var editable by remember { mutableStateOf(false) }
        TeamTitleHeader(teamName = teamName) { editable = !editable }
        AnimatedVisibility(visible = editable) {
            LazyColumn(Modifier.fillMaxHeight(listHeight) ){
                items(listOf( "one", "two", "three","one","one", "two", "three","two", "three","one", "two", "three","one", "two", "three")){ it
                    NewPlayerItem(name = it, salary ="8" )
                }
            }
        }
    }
}