package com.explore.r11.data.local.entities

import androidx.room.Entity

@Entity
data class MatchTeamPlayers(
    val matchId:Int,
    val teamId:Int,
    val playerId:Int
)
