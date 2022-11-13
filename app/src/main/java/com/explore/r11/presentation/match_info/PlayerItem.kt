package com.explore.r11.presentation.match_info

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.rounded.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.explore.r11.domain.model.Match
import com.explore.r11.domain.model.Player

@Composable
fun PlayerItem(
    player: Player,
//    onPlayerRemoved: (Player) -> Unit,
    match: String
) {
    Column( //shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
    )
    {

        Row(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(horizontal = 4.dp),

            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(0.2f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Rounded.Person,
//                    tint =
//                    if(match.teamOne == player.team){
//                        Color(0xFFb6defc)
//                    }
//                    else{
//                        Color(0xFFfecbb8)
//                    }, todo
                    contentDescription = null,
                    modifier = Modifier
                        .weight(1f)
                        .size(50.dp)

                )

                Text(
                    text = player.team,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.padding(bottom =2.dp)
                )

            }
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(0.35f)
                //.padding(horizontal = 10.dp)
                //verticalArrangement = Arrangement.Center
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically

                ) {

                    Text(
                        text = player.name,
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 15.dp, end = 2.dp)
                    )


                }
                Row (
                    Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start

                ){


                    Text(
                        text = player.type,
                        style = MaterialTheme.typography.caption,
                        fontSize = 10.sp,
                        modifier= Modifier.padding(start=15.dp, bottom = 2.5.dp, end = 4.dp),
                        textAlign = TextAlign.Center
                    )
                    Row(
                        Modifier
                            // .fillMaxWidth(0.5f)
                            .wrapContentHeight(),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        if(player.lastMatch ) {
                            RadioButton(
                                selected = true, onClick = {},
                                modifier = Modifier
                                    .height(25.dp)
                                    .width(12.dp)
                                    .scale(0.5f)
                                    .padding(start = 4.dp)
                            )
                            Spacer(modifier = Modifier.padding(end = 4.dp))
                            Text(
                                text = "Played Last Match",
                                style = MaterialTheme.typography.caption,
                                fontSize = 8.sp,
                                modifier = Modifier
                                    .wrapContentHeight()
                                //.padding(start = 4.dp)

                            )
                        }
                    }

                }
            }
            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(0.45f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                var checked by remember { mutableStateOf(player.isPlayerLocked) }
                var lockCvc by remember { mutableStateOf(player.isCvcLocked) }

                IconToggleButton(checked = lockCvc, onCheckedChange = {
//                    player.lockCvc(it) //todo
                    lockCvc = player.isCvcLocked
                    checked = player.isPlayerLocked
                }) {
                    val tint by animateColorAsState(if (player.isCvcLocked) Color(0xFFEC407A) else Color(0xFFB0BEC5))
                    Icon(Icons.Filled.Favorite, contentDescription = "Localized description", tint = tint)
                }

                IconToggleButton(checked = checked, onCheckedChange = {
//                    player.lockPlayer(it) //todo
                    checked = player.isPlayerLocked //if you update state variable, ui will update, even when it is not used in below block
                }) {
                    val tint by animateColorAsState(if (player.isPlayerLocked) Color(0xFFEC407A) else Color(0xFFB0BEC5))
                    Icon(Icons.Filled.Lock, contentDescription = "Localized description", tint = tint)
                }

//                IconButton(onClick = { onPlayerRemoved(player) }) {
//                    Icon(Icons.Outlined.Clear, contentDescription = "Localized description", tint = Color(0xFFB0BEC5))
//                } todo
            }
        }
    }
}