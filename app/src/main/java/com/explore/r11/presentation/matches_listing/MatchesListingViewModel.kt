package com.explore.r11.presentation.matches_listing

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.explore.r11.domain.repository.CricketRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MatchesListingViewModel @Inject constructor(
    private val repository: CricketRepository
): ViewModel() {
    var state by mutableStateOf(MatchesListingState())

    init {
        getListOfMatches()
//        getPlayers(45756)
    }

    fun getListOfMatches(){
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            repository.getMatches().let {
                state = state.copy(matches = it)
            }
            state = state.copy(isLoading = false)
        }
    }

    fun getPlayers(matchId:Int){
        viewModelScope.launch {
            val players = repository.getPlayers(matchId)
            println(players);
        }
    }

}