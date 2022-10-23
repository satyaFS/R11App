package com.explore.r11.presentation.matches_listing

import com.explore.r11.domain.model.Match

data class MatchesListingState(
    val matches:List<Match> = emptyList(),
    val isLoading:Boolean = false,
    val isRefreshing:Boolean = false
)