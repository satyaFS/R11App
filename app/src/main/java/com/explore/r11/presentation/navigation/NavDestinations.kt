package com.explore.r11.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.explore.r11.presentation.matches_listing.MatchesListing

@Composable
fun NavDestination(navController: NavHostController){
    NavHost(navController = navController, startDestination = Screen.MatchesListing.route){
        composable(route = Screen.MatchesListing.route){
            MatchesListing(navController = navController)
        }
    }
}
