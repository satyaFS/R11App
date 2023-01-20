package com.explore.r11.data.local.entities

import androidx.room.Entity

@Entity(primaryKeys =  ["matchId","teamId", "playerId"])
data class MatchTeamPlayersEntity(
    val matchId:Int,
    val teamId:Int,
    val playerId:Int
)
