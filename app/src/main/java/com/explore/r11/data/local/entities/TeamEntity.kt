package com.explore.r11.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TeamEntity(
    @PrimaryKey(autoGenerate = true)
    val teamId:Int,
    val teamName:String
)
