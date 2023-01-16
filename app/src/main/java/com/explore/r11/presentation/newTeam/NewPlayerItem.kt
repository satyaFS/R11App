package com.explore.r11.presentation.newTeam

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NewPlayerItem(name:String, salary:String){
    Row(Modifier.fillMaxWidth()) {

        var edit by remember{ mutableStateOf(false) }
        OutlinedTextField(
            value =  name,
            onValueChange = {},
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
        val options = listOf("BAT", "BL", "WK", "AR")
        var expanded by remember { mutableStateOf(false) }
        var selectedOptionText by remember { mutableStateOf(options[0]) }
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
                value = selectedOptionText,
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
                            selectedOptionText = selectionOption
                            expanded = false
                        }
                    ) {
                        Text(text = selectionOption)
                    }
                }
            }
        }
        OutlinedTextField(
            value =  salary,
            onValueChange = {},
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
    }
}