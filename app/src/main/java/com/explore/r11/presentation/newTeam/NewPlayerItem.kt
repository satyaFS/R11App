package com.explore.r11.presentation.newTeam

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.rounded.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
//        IconToggleButton(checked = edit,
//                onCheckedChange = { if(edit)
//                                    {
//                                        edit = !edit
//                                        focusRequester.freeFocus()
//                                    }
//                                    else{
//                                    edit = !edit
//                                       }
//        }) {
//            if(!edit)
//                Icon(Icons.Default.Edit, contentDescription = "Localized description", tint = Color.Black)
//            else
//                Icon(Icons.Default.ThumbUp, "", tint = Color.Green)
//        }
//        SideEffect {
//            focusRequester.requestFocus()
//        }
    }
}