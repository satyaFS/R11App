package com.explore.r11.presentation.newTeam

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun TeamTitleHeader(teamName:String, expand:()->Unit){
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(80.dp)
        .clickable { expand() }
        , elevation = 5.dp

    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight()
            , Arrangement.Center) {

            Row(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.20f)
                // .background(matchCardColor),
                , Arrangement.Center,
                Alignment.CenterVertically

            )
            {
                Text(
                    text =  "league",
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .align(Alignment.CenterVertically),
                    textAlign = TextAlign.Center
                )
            }

            Row(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(1f)
                , Arrangement.SpaceBetween, Alignment.CenterVertically) {
                var edit by remember{ mutableStateOf(false) }
                OutlinedTextField(
                    value =  teamName,
                    onValueChange = {},
                    enabled = edit,
                    textStyle = MaterialTheme.typography.body1,
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Gray,
                            unfocusedBorderColor = White,
                            textColor = DarkGray,
                            disabledTextColor = Black)
                )
                IconToggleButton(checked = edit, onCheckedChange = {edit = !edit}) {
//                    val tint by animateColorAsState(if (edit) Color(0xFFEC407A) else Color(0xFFB0BEC5))
                    if(!edit)
                        Icon(Icons.Default.Edit, contentDescription = "Localized description", tint = Black)
                    else
                        Icon(Icons.Default.ThumbUp, "", tint = Color.Green)
                }

            }

        }
    }
}
