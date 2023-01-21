package com.explore.r11.presentation.newTeam

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.explore.r11.data.local.entities.TeamEntity
import com.explore.r11.data.mapper.toNewPlayer
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


    fun saveMatchInfo(){
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val matchAndTeamKeys = repository.saveMatchInfo(
                state.matchId,
                state.teamOnePlayers,
                state.teamTwoPlayers,
                TeamEntity(state.teamOneId, state.teamOneName),
                TeamEntity(state.teamTwoId, state.teamTwoName)
            )
            val players = repository.getPlayers(matchAndTeamKeys[0])
            val teamOnePlayers = players.filter { it.team == state.teamOneName }.map { it.toNewPlayer() }
            val teamTwoPlayers = players.filter { it.team == state.teamTwoName }.map { it.toNewPlayer() }
            state = state.copy(
                matchId = matchAndTeamKeys[0],
                teamOneId = matchAndTeamKeys[1],
                teamTwoId = matchAndTeamKeys[2],
                teamOnePlayers = teamOnePlayers,
                teamTwoPlayers = teamTwoPlayers
            )
            state = state.copy(isLoading = false)
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
            val player = NewPlayer(0,"Player $count", state.teamOneName, "Bat", 8.0)
            val teamOne = state.teamOnePlayers.toMutableList()
            teamOne.add(player)
            state.copy(teamOnePlayers = teamOne, teamOneCount = count)
        } else{
            val count = state.teamTwoCount +1
            val player = NewPlayer(0,"Player $count", state.teamTwoName, "Bat", 8.0)
            val teamTwo = state.teamTwoPlayers.toMutableList()
            teamTwo.add(player)
            state.copy(teamTwoPlayers = teamTwo, teamTwoCount = count)
        }
    }

}