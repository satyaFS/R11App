package com.explore.r11.presentation.navigation

sealed class Screen(val route:String){
    object Players :Screen("allPlayers")
    object GeneratedTeams:Screen("GeneratedTeams")
    object MatchesListing:Screen("matchesListing")
    object AddMatch:Screen("addMatch")

}
