package com.explore.r11.presentation.newTeam

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.rounded.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NewPlayerItem(name:String, salary:String){
    Row(Modifier.fillMaxWidth()) {
        var edit by remember{ mutableStateOf(false) }
        OutlinedTextField(
            value =  name,
            onValueChange = {},
            enabled = edit,
            textStyle = MaterialTheme.typography.body1,
            modifier = Modifier
                .weight(0.6f)
                .align(Alignment.CenterVertically),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Gray,
                unfocusedBorderColor = Color.White,
                textColor = Color.DarkGray,
                disabledTextColor = Color.Black)
        )
        OutlinedTextField(
            value =  salary,
            onValueChange = {},
            enabled = edit,
            textStyle = MaterialTheme.typography.body1,
            modifier = Modifier
                .weight(0.20f)
                .align(Alignment.CenterVertically),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Gray,
                unfocusedBorderColor = Color.White,
                textColor = Color.DarkGray,
                disabledTextColor = Color.Black)
        )
        IconToggleButton(checked = edit, onCheckedChange = {edit = !edit}) {
            if(!edit)
                Icon(Icons.Default.Edit, contentDescription = "Localized description", tint = Color.Black)
            else
                Icon(Icons.Default.ThumbUp, "", tint = Color.Green)
        }
    }
}