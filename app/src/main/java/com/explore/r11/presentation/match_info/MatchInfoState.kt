package com.explore.r11.presentation.match_info

import com.explore.r11.domain.model.Match

data class MatchInfoState(
    val match:Match? = null,
    val isLoading:Boolean = false,
)