package com.explore.r11.presentation.newTeam

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
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
}