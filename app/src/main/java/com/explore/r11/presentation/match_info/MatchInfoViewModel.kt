package com.explore.r11.presentation.match_info

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import com.explore.r11.domain.model.Match
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
        if(matchId?.isDigitsOnly() == true){
            initializeState(matchId)
        }
    }

    fun initializeState(matchId:String){
        viewModelScope.launch {
            state = state.copy(matchId = matchId)
            state = state.copy(isLoading = true)
            val players = repository.getPlayers(matchId.toInt());
            state = state.copy(selectedPlayers = players)
            state = state.copy(isLoading = false)
        }
    }

    fun removePlayer(player:Player){
        val selectedPlayers =   state.selectedPlayers.toMutableList()
        selectedPlayers.remove(player)
        state = state.copy(selectedPlayers = selectedPlayers.toList())
    }

    fun addPlayer(player: Player){
        val removedPlayers = state.removedPlayers.toMutableList()
        removedPlayers.remove(player)
        val selectedPalyers = state.selectedPlayers.toMutableList()
        selectedPalyers.add(player)
        state = state.copy(removedPlayers = removedPlayers, selectedPlayers = selectedPalyers)
    }

}