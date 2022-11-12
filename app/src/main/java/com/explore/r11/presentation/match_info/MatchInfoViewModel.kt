package com.explore.r11.presentation.match_info

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.explore.r11.domain.model.Match
import com.explore.r11.domain.repository.CricketRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MatchInfoViewModel @Inject constructor (
    val repository: CricketRepository
):ViewModel() {
    var state by mutableStateOf(MatchInfoState());
    init {

    }
}