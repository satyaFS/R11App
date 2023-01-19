package com.explore.r11.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(primaryKeys =  ["matchId","teamId", "playerId"])
data class MatchTeamPlayers(
    val matchId:Int,
    val teamId:Int,
    val playerId:Int
)
