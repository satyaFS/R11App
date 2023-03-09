package com.explore.r11.data.local.entities

import androidx.room.Entity

@Entity(primaryKeys = ["matchId", "setNo","teamNo","playerId", "teamId"])
data class GeneratedTeamsEntity(
    val matchId :Int,
    val setNo   :Int,
    val teamNo  :Int,
    val playerId:Int,
    val isCaptain:Boolean,
    val isViceCaptain:Boolean,
    val teamId  : Int
)