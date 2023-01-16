package com.explore.r11.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PlayerEntity(
    @PrimaryKey
    val playerId:Int,
    val playerName:String,
    val playerSalary:Double,
    val playerType:String
)
