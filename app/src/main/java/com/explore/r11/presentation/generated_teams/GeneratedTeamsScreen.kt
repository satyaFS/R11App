package com.explore.r11.presentation.generated_teams

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun GeneratedTeamsScreen(navController: NavController ,viewModel: GenerateTeamsViewModel  ){
    val state = viewModel.state
    val bool = remember{ mutableStateOf(true) }
    val openDialog = remember{ mutableStateOf(false) }
    BackHandler(enabled = bool.value, onBack = {openDialog.value = true})

    LazyColumn(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(),contentPadding = PaddingValues(1.dp)
    ){
        itemsIndexed(state.teams){index,player->
            GeneratedTeamCard(player, state.teamOne, index)
        }

    }
    if (openDialog.value ) {
        AlertDialog(
            onDismissRequest = {
                // Dismiss the dialog when the user clicks outside the dialog or on the back
                // button. If you want to disable that functionality, simply use an empty
                // onCloseRequest.
                openDialog.value = false
            },
            title = {
                Text(text = "Hope you have created your Teams !!")
            },
            text = {
                Text(
                    "If you go back now, you will lose your generated teams"
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                        bool.value = false
                    }
                ) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                TextButton(onClick = { openDialog.value = false }) {
                    Text("Cancel")
                }
            }

        )
    }
}