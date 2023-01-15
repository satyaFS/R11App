package com.explore.r11.presentation.newTeam

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.explore.r11.domain.model.NewPlayer
import com.explore.r11.domain.model.Player
import com.explore.r11.domain.repository.CricketRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewTeamViewModel @Inject constructor(
    repository: CricketRepository
):ViewModel()  {
    var state by mutableStateOf(NewTeamState())
    init {
        state = state.copy(teamOneName = "Mumbai Indians", teamTwoName = "Chennai Super Kings")
    }
    /*
    fun addPlayer(name:String, type:String, salary:Int, teamName:String, oneOrTwo:Int){
        val player = Player(name, type = type, salary = salary.toString(), team = teamName)
        state = if(oneOrTwo == 1){
            val teamOne = state.teamOnePlayers.toMutableList()
            teamOne.add(player)
            state.copy(teamOnePlayers = teamOne)
        } else{
            val teamTwo = state.teamTwoPlayers.toMutableList()
            teamTwo.add(player)
            state.copy(teamTwoPlayers = teamTwo)
        }
    }
    */
    fun updateName(player: NewPlayer, name: String){
        player.name = name
    }
    fun updateType(player: NewPlayer, type:String){
        player.type = type
    }
    fun updateSalary(player: NewPlayer, salary:Int){
        player.salary = salary
    }
    fun addPlayer(oneOrTwo:Int, teamName:String){
        state = if(oneOrTwo == 1){
            val count = state.teamOneCount +1
            val player = NewPlayer("Player $count", teamName, "Bat", 8)
            val teamOne = state.teamOnePlayers.toMutableList()
            teamOne.add(player)
            state.copy(teamOnePlayers = teamOne, teamOneCount = count)
        } else{
            val count = state.teamTwoCount +1
            val player = NewPlayer("Player $count", teamName, "Bat", 8)
            val teamTwo = state.teamTwoPlayers.toMutableList()
            teamTwo.add(player)
            state.copy(teamTwoPlayers = teamTwo, teamTwoCount = count)
        }
    }
}