package com.explore.r11.presentation.generated_teams

import com.explore.r11.domain.model.Player

data class GeneratedTeamsState(
    val teams:List<List<Player>> = emptyList(),
    val teamOne:String ="",
    val isLoading:Boolean = false
)