package com.explore.r11.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.explore.r11.presentation.generated_teams.GenerateTeamsViewModel
import com.explore.r11.presentation.generated_teams.GeneratedTeamsScreen
import com.explore.r11.presentation.match_info.MatchInfoScreen
import com.explore.r11.presentation.match_info.MatchInfoViewModel
import com.explore.r11.presentation.matches_listing.MatchesListing
import com.explore.r11.presentation.newTeam.NewMatchScreen

@Composable
fun NavDestination(navController: NavHostController){
    NavHost(navController = navController, startDestination = Screen.MatchesListing.route){
        composable(route = Screen.MatchesListing.route){
            MatchesListing(navController = navController)
        }
        composable(Screen.Players.route +"/{matchId}/{leagueName}"){
            MatchInfoScreen(navController = navController)
        }
        composable(Screen.GeneratedTeams.route+"/{noOfTeams}"){
           GeneratedTeamsScreen(navController)
        }
        composable(Screen.AddMatch.route){
            NewMatchScreen(navController = navController)
        }
    }
}
