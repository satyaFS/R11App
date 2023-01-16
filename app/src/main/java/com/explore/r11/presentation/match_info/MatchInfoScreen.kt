package com.explore.r11.presentation.match_info

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.explore.r11.domain.model.Player
import com.explore.r11.presentation.matches_listing.MatchCard
import com.explore.r11.presentation.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun MatchInfoScreen(
    viewModel: MatchInfoViewModel = hiltViewModel(),
    navController:NavController
){
    val state = viewModel.state
    if(state.isLoading)
        Box(Modifier.fillMaxSize(), Alignment.Center){
            CircularProgressIndicator()
        }
    else {
        var selectedIndex by remember { mutableStateOf(0) }
        Column(Modifier.fillMaxSize()) {
            Divider(thickness = 1.dp, color = Color.LightGray)
            MatchCard( state.league, state.teamOne,state.teamTwo )
            Divider(thickness = 1.dp, color = Color.LightGray)
            TabRow(selectedTabIndex = selectedIndex, backgroundColor = MaterialTheme.colors.surface) {
                Tab(
                    selected = true,
                    onClick = { selectedIndex = 0 },
                    text = { Text(text = "All Players") }
                )
                Tab(selected = true, onClick = { selectedIndex = 1 }, text = {Text(text = "Removed Players")})
            }
            if(selectedIndex==0){
                Column {
                    Row(Modifier.background(Color.LightGray)) {
                        Spacer(modifier = Modifier.weight(0.2f))
                        Text(
                            text = "Player",
                            style = MaterialTheme.typography.caption,
                            modifier = Modifier
                                .weight(0.35f)
                                .padding(start = 14.dp, bottom = 8.dp, top = 4.dp)//.background(Color.Red)
                                .align(Alignment.CenterVertically),
                            textAlign = TextAlign.Start
                        )
                        Text(
                            text = "  C/Vc",
                            style = MaterialTheme.typography.caption,
                            modifier = Modifier
                                .weight(0.15f)
                                .padding(bottom = 8.dp, top = 4.dp) //.background(Color.Yellow)
                                .align(Alignment.CenterVertically),
                            textAlign = TextAlign.Start
                        )
                        Text(
                            text = "Lock Player",
                            style = MaterialTheme.typography.caption,
                            modifier = Modifier
                                .weight(0.3f)
                                .padding(bottom = 8.dp, top = 4.dp) //.background(Color.Magenta)
                                .align(Alignment.CenterVertically),
                            textAlign = TextAlign.Start
                        )
                    }
                    Divider(color = Color.LightGray, thickness = 1.dp)
                    LazyColumn(
                        Modifier
                            .fillMaxWidth()
                            .weight(1f)) {
                        items(state.selectedPlayers) {
                            PlayerItem(player = it, viewModel::removePlayer, viewModel::lockCVC,viewModel::lockPlayer,viewModel.state.matchId)
                        }
                    }
                    Row(Modifier.fillMaxWidth().wrapContentHeight()  ) {
                        SelectNoOfTeams(navController = navController )
                    }

                }
            }
            else {
                Column {
                    Row(Modifier.background(Color.LightGray)) {
                        Spacer(modifier = Modifier.weight(0.2f))
                        Text(
                            text = "Player",
                            style = MaterialTheme.typography.caption,
                            modifier = Modifier
                                .weight(0.35f)
                                .padding(start = 14.dp, bottom = 8.dp, top = 4.dp)//.background(Color.Red)
                                .align(Alignment.CenterVertically),
                            textAlign = TextAlign.Start
                        )

                    }
                    Divider(color = Color.LightGray, thickness = 1.dp)
                    LazyColumn(
                        Modifier
                            .fillMaxWidth()
                            .weight(1f)) {
                        items(state.removedPlayers) {
                            RemovedPlayerItem(player = it, viewModel::addPlayer,viewModel.state.matchId)
                        }
                    }
                    Row(Modifier.fillMaxWidth().wrapContentHeight()  ) {
                        SelectNoOfTeams(navController = navController )
                    }
                }
            }
        }
    }
}


@Composable
fun SelectNoOfTeams(
    navController: NavController,
//    generateTeams:(List<Player>, noOfTeams:Int)->Unit,
//    validateSelectedPlayers:()->Int,

    ){
    val openDialog = rememberSaveable{ mutableStateOf(1) }
    var numberValue by remember { mutableStateOf(50)}

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        Row(
            Modifier
                .fillMaxWidth()
                .height(50.dp)
            ,verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            Button(
                onClick = {
//                    openDialog.value = validateSelectedPlayers() todo
                    if( openDialog.value== 1){
//                        generateTeams(players.toList(), numberValue)
                        navController.navigate(Screen.GeneratedTeams.route)
                    }
                },
                modifier = Modifier
                    .weight(0.5f)
                    .fillMaxHeight()
                    .border(1.dp, Color.White)

            ) {
                Text(text = "Generate Teams",
                    Modifier
                        .align(Alignment.CenterVertically))

            }
            Row( verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .weight(0.3f)
                    .fillMaxHeight()
                    .background(Color(0xFF6e7ef6))
                    .border(1.dp, Color.White)
            ){
                IconButton(onClick = {
                    numberValue = if ((numberValue - 1) > 1) numberValue-1 else 1
                } ,  modifier = Modifier.weight(0.25f)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.KeyboardArrowDown,
                        tint = Color.White,
                        contentDescription = null,
                    )
                }

                Text(
                    "$numberValue",
                    color = Color.White,
                    fontWeight = FontWeight.Bold ,modifier = Modifier.weight(0.5f)
                    , textAlign = TextAlign.Center
                )

                IconButton(onClick = { numberValue = if ((numberValue + 1) <1000 ) numberValue+1 else 1000 },
                    modifier = Modifier.weight(0.25f)) {
                    Icon(
                        imageVector = Icons.Rounded.KeyboardArrowUp,
                        tint = Color.White,
                        contentDescription = null
                    )
                }
            }
            Column(
                Modifier
                    .fillMaxHeight()
                    .weight(0.2f)
                    .border(1.dp, Color.White)


            ) {
                Button(
                    onClick = { numberValue = if ((numberValue + 50) <1000 ) numberValue+50 else 1000 },
                    modifier = Modifier
                        .weight(0.5f)
                        .fillMaxWidth(), contentPadding = PaddingValues(0.dp)
                ) {
                    Text(
                        text = "+ 50",
                        style =  MaterialTheme.typography.caption,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 12.sp
                    )
                }
                Spacer(modifier = Modifier.padding(1.dp))
                Button(
                    onClick = { numberValue = if ((numberValue - 50) > 1 ) numberValue-50 else 1 },
                    modifier = Modifier
                        .weight(0.5f)
                        .fillMaxWidth(), contentPadding = PaddingValues(0.dp)
                ) {
                    Text(text = "- 50", style = MaterialTheme.typography.caption,fontSize = 12.sp, fontWeight = FontWeight.ExtraBold)
                }
            }

        }
        if (openDialog.value != 1) {
            AlertDialog(
                onDismissRequest = {
                    // Dismiss the dialog when the user clicks outside the dialog or on the back
                    // button. If you want to disable that functionality, simply use an empty
                    // onCloseRequest.
                    openDialog.value = 1
                },
                title = {
                    Text(text = "Its good to follow few rules")
                },
                text = {
                    Text(
                        "Select minimum of 1 WK, 3 BAT, 1 ALL, 3 BOWL and 4 players from each team." +
                                "Do not lock more than 11 players."
                    )
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            openDialog.value = 1
                        }
                    ) {
                        Text("Confirm")
                    }
                }
            )
        }
    }
}
