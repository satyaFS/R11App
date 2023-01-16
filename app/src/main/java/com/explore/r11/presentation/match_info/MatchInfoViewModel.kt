package com.explore.r11.presentation.match_info

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.explore.r11.domain.model.Player
import com.explore.r11.domain.repository.CricketRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MatchInfoViewModel @Inject constructor (
    savedStateHandle: SavedStateHandle,
    val repository: CricketRepository
):ViewModel() {
    var state by mutableStateOf(MatchInfoState());

    init {
        val matchId = savedStateHandle.get<String>("matchId")
        println(matchId)
        val league = savedStateHandle.get<String>("leagueName")

        if(matchId?.isDigitsOnly() == true && league != null){
            initializeState(matchId, league)
        }
    }

    fun initializeState(matchId:String, league:String){
        viewModelScope.launch {
            state = state.copy(matchId = matchId)
            state = state.copy(isLoading = true)
            val players = repository.getPlayers(matchId.toInt());
            if(players.isNotEmpty()){
                val teamOne = players[0].team
                val teamTwo = players.first { it.team != teamOne }.team
                state = state.copy(league = league, teamOne = teamOne, teamTwo = teamTwo)
                state = state.copy(selectedPlayers = players)
            }
            state = state.copy(isLoading = false)
        }
    }

    fun removePlayer(player:Player){
        val selectedPlayers =   state.selectedPlayers.toMutableList()
        selectedPlayers.remove(player)
        val removedPlayers = state.removedPlayers.toMutableList()
        removedPlayers.add(player)
        state = state.copy(selectedPlayers = selectedPlayers.toList(), removedPlayers = removedPlayers)
    }

    fun addPlayer(player: Player){
        val removedPlayers = state.removedPlayers.toMutableList()
        removedPlayers.remove(player)
        val selectedPlayers = state.selectedPlayers.toMutableList()
        selectedPlayers.add(player)
        state = state.copy(removedPlayers = removedPlayers, selectedPlayers = selectedPlayers)
    }

    fun lockCVC(player: Player){
        player.isCvcLocked = !player.isCvcLocked
    }
    fun lockPlayer(player:Player){
        player.isPlayerLocked = !player.isPlayerLocked
    }




}