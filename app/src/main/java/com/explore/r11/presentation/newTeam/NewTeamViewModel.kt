package com.explore.r11.presentation.newTeam

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.explore.r11.domain.model.NewPlayer
import com.explore.r11.domain.model.Player
import com.explore.r11.domain.repository.CricketRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewTeamViewModel @Inject constructor(
   private val repository: CricketRepository
):ViewModel()  {
    var state by mutableStateOf(NewTeamState())

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
    fun saveMatchInfo(){
        viewModelScope.launch {
            repository.saveMatchInfo(state.teamOnePlayers, state.teamTwoPlayers, state.teamOneName, state.teamTwoName)
        }
    }

    fun updateTeamName(oneOrTwo: Int, teamName: String){
        state = if(oneOrTwo == 1){
            state.copy(teamOneName = teamName)
        }
        else state.copy(teamTwoName = teamName)
    }
    fun updatePlayerName(player: NewPlayer, name: String){
        player.name = name
    }
    fun updatePlayerType(player: NewPlayer, type:String){
        player.type = type
    }
    fun updatePlayerSalary(player: NewPlayer, salary:Double){
        player.salary = salary
    }
    fun addPlayer(oneOrTwo:Int){
        state = if(oneOrTwo == 1){
            val count = state.teamOneCount +1
            val player = NewPlayer("Player $count", state.teamOneName, "Bat", 8.0)
            val teamOne = state.teamOnePlayers.toMutableList()
            teamOne.add(player)
            state.copy(teamOnePlayers = teamOne, teamOneCount = count)
        } else{
            val count = state.teamTwoCount +1
            val player = NewPlayer("Player $count", state.teamTwoName, "Bat", 8.0)
            val teamTwo = state.teamTwoPlayers.toMutableList()
            teamTwo.add(player)
            state.copy(teamTwoPlayers = teamTwo, teamTwoCount = count)
        }
    }

}