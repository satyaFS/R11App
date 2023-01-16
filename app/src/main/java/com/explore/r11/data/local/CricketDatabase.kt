package com.explore.r11.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.explore.r11.data.local.entities.MatchEntity
import com.explore.r11.data.local.entities.MatchTeamPlayers
import com.explore.r11.data.local.entities.PlayerEntity
import com.explore.r11.data.local.entities.TeamEntity

@Database(
    entities = [MatchEntity::class, TeamEntity::class, PlayerEntity::class, MatchTeamPlayers::class],
    version = 1
)
abstract class CricketDatabase:RoomDatabase() {
    abstract val cricketDao:CricketDao
}