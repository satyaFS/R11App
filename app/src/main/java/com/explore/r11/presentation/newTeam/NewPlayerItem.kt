package com.explore.r11.presentation.newTeam

import android.widget.EditText
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.explore.r11.domain.model.NewPlayer
import com.explore.r11.domain.model.PlayerType

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NewPlayerItem(
    player: NewPlayer,
    updatePlayerName: (NewPlayer, String) -> Unit,
    updatePlayerSalary: (NewPlayer, Double) -> Unit,
    updatePlayeType:(NewPlayer, String)->Unit
){
    var playerName by remember { mutableStateOf(player.name) }
    var playerSalary by remember { mutableStateOf(player.salary) }
    var playerType by remember { mutableStateOf(player.type) }

    Row(Modifier.fillMaxWidth().height(IntrinsicSize.Min)) {//imp
        var edit by remember{ mutableStateOf(false) }
        OutlinedTextField(
            value =  playerName,
            onValueChange = {updatePlayerName(player,it)
                            playerName = player.name},
            enabled = true,
            textStyle = MaterialTheme.typography.body1,
            modifier = Modifier
                .weight(0.5f)
                .align(Alignment.CenterVertically),

            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Gray,
                unfocusedBorderColor = Color.White,
                textColor = Color.DarkGray,
                disabledTextColor = Color.Black)
        )
        val options = listOf(PlayerType.Batter.type, PlayerType.Bowler.type, PlayerType.AllRounder.type, PlayerType.WicketKeeper.type)
        var expanded by remember { mutableStateOf(false) }
// We want to react on tap/press on TextField to show menu
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            },
            modifier = Modifier.weight(0.3f)
        ) {
            TextField(
                readOnly = true,
                value = playerType,
                onValueChange = { },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expanded
                    )
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors(backgroundColor = Color.White)
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = {
                    expanded = false
                }
            ) {
                options.forEach { selectionOption ->
                    DropdownMenuItem(
                        onClick = {
                            updatePlayeType(player,selectionOption)
                            playerType = player.type
                            expanded = false
                        }
                    ) {
                        Text(text = selectionOption)
                    }
                }
            }
        }

        Row( verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .weight(0.3f)
                .fillMaxHeight()
                .background(Color(0xFF6e7ef6))
        ){
            IconButton(onClick = {
                updatePlayerSalary(player, if ((player.salary - 0.5) >= 6) player.salary-0.5 else 6.0 )
                playerSalary = player.salary
            } ,  modifier = Modifier.weight(0.25f)
            ) {
                Icon(
                    imageVector = Icons.Rounded.KeyboardArrowDown,
                    tint = Color.White,
                    contentDescription = null,
                )
            }

            Text(
                "$playerSalary",
                color = Color.White,
                fontWeight = FontWeight.Bold ,modifier = Modifier.weight(0.5f)
                , textAlign = TextAlign.Center
            )

            IconButton(onClick = {
                                 updatePlayerSalary(player,if ((player.salary + 0.5) <= 12.0 ) player.salary+0.5 else 12.0)
                                 playerSalary = player.salary
                                 },
                modifier = Modifier.weight(0.25f)) {
                Icon(
                    imageVector = Icons.Rounded.KeyboardArrowUp,
                    tint = Color.White,
                    contentDescription = null
                )
            }
        }


    }
}