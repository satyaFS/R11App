package com.explore.r11.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MatchEntity(
    @PrimaryKey
    val matchId:Int,
    val teamOneId:Int,
    val teamTwoId:Int,
    val leagueName:Int
)