package com.explore.r11.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SelectedPlayersEntity(
    @PrimaryKey(autoGenerate = true)
    val playerId:Int = 0,
    val playerName:String,
    val teamName:String,
    val playerType:String,
    val playerSalary:String,
    val isPlayerLocked:Boolean = false,
    val isCvcLocked:Boolean = false,
    val isCaptain:Boolean = false,
    val isViceCaptain:Boolean = false
)
