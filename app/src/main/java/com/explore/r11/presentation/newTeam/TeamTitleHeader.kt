package com.explore.r11.presentation.newTeam

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TeamTitleHeader(teamName:String, expand:()->Unit){
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(60.dp)
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
                TextField(
                    value =  teamName,
                    onValueChange = {},
                    textStyle = MaterialTheme.typography.body1,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterVertically),
                )
            }

        }
    }
}
