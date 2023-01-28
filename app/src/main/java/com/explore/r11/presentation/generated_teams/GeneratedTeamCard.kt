package com.explore.r11.presentation.generated_teams

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.rounded.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.explore.r11.domain.model.Player

@Composable
fun GeneratedTeamCard(generatedTeams:List<Player>,teamOne:String,index:Int){
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(500.dp)
        .padding(bottom = 5.dp)
        .background(Color.Black))
    {
        val iconOne = Color(0xFF8cf000)
        val iconTwo = Color(0xFFf0008c)
        Column(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Color(0xFF6300f0)), verticalArrangement = Arrangement.SpaceAround) {
            Text(
                text = "${index +1}",
                textAlign = TextAlign.Start,
                color = Color.White,
                modifier = Modifier.padding(start = 10.dp)
            )
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(items = generatedTeams.filter { it.playerType=="WK" }.chunked(3)){
                        player->
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 5.dp), Arrangement.SpaceEvenly) {
                        for (team in player){
                            Column(modifier = Modifier.height(50.dp), Arrangement.Center, Alignment.CenterHorizontally) {
                                Icon(
                                    imageVector = Icons.Filled.Person,
                                    tint =
                                    if( teamOne == team.teamName){
                                        iconTwo
                                    }
                                    else{
                                        iconOne
                                    },
                                    contentDescription = null,
                                    modifier = Modifier
                                        .weight(1f)
                                        .size(30.dp)

                                )
                                Text(
                                    text = when {
                                        team.isCaptain -> {team.playerName + " ( C )"}
                                        team.isViceCaptain -> {team.playerName +" ( Vc )"}
                                        else -> team.playerName
                                    },
                                    color =
                                    if(team.isCaptain || team.isViceCaptain){
                                        Color.Yellow
                                    }else  Color.White,
                                    fontWeight = FontWeight.Bold
                                    //modifier = Modifier.padding(end = 20.dp),
                                    //overflow = TextOverflow.Visible
                                )
                            }
                        }
                    }
                }

            }
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(items = generatedTeams.filter { it.playerType=="BAT" }.chunked(3)){
                        player->
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 5.dp), Arrangement.SpaceEvenly) {
                        for (team in player){
                            Column(modifier = Modifier.height(50.dp), Arrangement.Center, Alignment.CenterHorizontally) {
                                Icon(
                                    imageVector = Icons.Rounded.Person,
                                    tint =
                                    if( teamOne == team.teamName){
                                        iconTwo

                                    }
                                    else{
                                        iconOne
                                    },
                                    contentDescription = null,
                                    modifier = Modifier
                                        .weight(1f)
                                        .size(30.dp)

                                )
                                Text(
                                    text = when {
                                        team.isCaptain -> {team.playerName + " ( C )"}
                                        team.isViceCaptain -> {team.playerName +" ( Vc )"}
                                        else -> team.playerName
                                    },
                                    color =
                                    if(team.isCaptain || team.isViceCaptain){
                                        Color.Yellow
                                    }else  Color.White,

                                    fontWeight = FontWeight.Bold

                                    //modifier = Modifier.padding(end = 20.dp),
                                    //overflow = TextOverflow.Visible
                                )
                            }
                        }
                    }
                }
            }
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(items = generatedTeams.filter { it.playerType=="ALL" }.chunked(3)){
                        player->
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 5.dp), Arrangement.SpaceEvenly) {
                        for (team in player){
                            Column(modifier = Modifier.height(50.dp), Arrangement.Center, Alignment.CenterHorizontally) {
                                Icon(
                                    imageVector = Icons.Rounded.Person,
                                    tint =
                                    if( teamOne == team.teamName){
                                        iconTwo
                                    }
                                    else{
                                        iconOne
                                    },
                                    contentDescription = null,
                                    modifier = Modifier
                                        .weight(1f)
                                        .size(30.dp)

                                )
                                Text(
                                    text = when {
                                        team.isCaptain -> {team.playerName + " ( C )"}
                                        team.isViceCaptain -> {team.playerName +" ( Vc )"}
                                        else -> team.playerName
                                    },
                                    color =
                                    if(team.isCaptain || team.isViceCaptain){
                                        Color.Yellow
                                    }else  Color.White,
                                    fontWeight = FontWeight.Bold
                                    //modifier = Modifier.padding(end = 20.dp),
                                    //overflow = TextOverflow.Visible
                                )
                            }
                        }
                    }
                }
            }
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(items = generatedTeams.filter { it.playerType=="BOWL" }.chunked(3)){
                        player->
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 5.dp), Arrangement.SpaceEvenly) {
                        for (team in player){
                            Column(modifier = Modifier.height(50.dp), Arrangement.Center, Alignment.CenterHorizontally) {
                                Icon(
                                    imageVector = Icons.Rounded.Person,
                                    tint =
                                    if( teamOne == team.teamName){
                                        iconTwo
                                    }
                                    else{
                                        iconOne
                                    },
                                    contentDescription = null,
                                    modifier = Modifier
                                        .weight(1f)
                                        .size(30.dp)

                                )
                                Text(
                                    text = when {
                                        team.isCaptain -> {team.playerName + " ( C )"}
                                        team.isViceCaptain -> {team.playerName +" ( Vc )"}
                                        else -> team.playerName
                                    },
                                    color =
                                    if(team.isCaptain || team.isViceCaptain){
                                        Color.Yellow
                                    }else  Color.White,
                                    fontWeight = FontWeight.Bold
                                    //modifier = Modifier.padding(end = 20.dp),
                                    //overflow = TextOverflow.Visible
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

