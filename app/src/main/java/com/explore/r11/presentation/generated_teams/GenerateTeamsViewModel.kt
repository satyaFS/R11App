package com.explore.r11.presentation.generated_teams

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.explore.r11.domain.model.Player
import com.explore.r11.domain.model.PlayersCombination
import com.explore.r11.domain.repository.CricketRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenerateTeamsViewModel @Inject constructor (
    private val repository: CricketRepository,
    private val savedStateHandle: SavedStateHandle
) :ViewModel() {
    var state by mutableStateOf(GeneratedTeamsState())
    init {
        initializeState()
    }

    private fun initializeState(){
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val noOfTeams = savedStateHandle.get<String>("noOfTeams")
            val players = repository.getSelectedPlayers()
            println("32")
            println(players)
            if(noOfTeams?.isDigitsOnly() == true){
                val teams = random11(players, noOfTeams.toInt())
                state = state.copy(teams = teams)
            }
            state = state.copy(isLoading = false)
        }
    }
    private fun random11(list: List<Player>, noOfTeams:Int): List<List<Player>> {
        val possiblePlayer: List<PlayersCombination> = listOf(
            PlayersCombination(1, 3, 3, 4),
            PlayersCombination(1, 3, 2, 5),
            PlayersCombination(1, 4, 3, 3),
            PlayersCombination(1, 4, 2, 4),
            PlayersCombination(1, 4, 1, 5),
            PlayersCombination(1, 5, 2, 3),
            PlayersCombination(1, 5, 1, 4),
            PlayersCombination(1, 3, 4, 3),
            PlayersCombination(1, 6, 1, 3),
            PlayersCombination(2, 3, 1, 5),
            PlayersCombination(2, 3, 2, 4),
            PlayersCombination(2, 4, 1, 4),
            PlayersCombination(2, 4, 2, 3),
            PlayersCombination(2, 5, 1, 3),
            PlayersCombination(3, 3, 1, 4),
            PlayersCombination(3, 3, 2, 3),
            PlayersCombination(3, 4, 1, 3),
            PlayersCombination(4, 3, 1, 3),
        )
        //max number of players possible for each player type
        val wkList = list.filter { it.playerType == "WK" }
        val batList = list.filter { it.playerType == "BAT" }
        val bowList = list.filter { it.playerType == "BOWL" }
        val arList = list.filter { it.playerType == "ALL" }

        println("${wkList.size}+${batList.size}+${bowList.size}+${arList.size}")
        //print(bowList)
        //number of players fixed in each player type
        val wkListFixed = wkList.count { it.isPlayerLocked }
        val batListFixed = batList.filter { it.isPlayerLocked }.count()
        val bowListFixed = bowList.filter { it.isPlayerLocked }.count()
        val arListFixed = arList.filter { it.isPlayerLocked }.count()
        val finalTeam = mutableListOf<Player>()
        val teamCombinationList = possiblePlayer.filter {
            (it.wk <= wkList.count() && it.wk >= wkListFixed) && (it.bat <= batList.count() && it.bat >= batListFixed)
                    && (it.bow <= bowList.count() && it.bow >= bowListFixed) && (it.ar <= arList.count() && it.ar >= arListFixed)
        }
        println("79")
        println(teamCombinationList.size)
        var duplicateTeams = 0
        var teamCount = 0
        val totalTeams: MutableList<List<Player>> = mutableListOf()

        while (totalTeams.count() < noOfTeams && duplicateTeams < 30) {
            val selectedCombination = teamCombinationList.random()

            finalTeam.addAll(list.filter { it.isPlayerLocked })
            finalTeam.addAll(
                wkList.filter { !it.isPlayerLocked }.asSequence().shuffled()
                    .take(selectedCombination.wk - wkListFixed)
            )
            finalTeam.addAll(
                batList.filter { !it.isPlayerLocked }.asSequence().shuffled()
                    .take(selectedCombination.bat - batListFixed)
            )
            finalTeam.addAll(
                bowList.filter { !it.isPlayerLocked }.asSequence().shuffled()
                    .take(selectedCombination.bow - bowListFixed)
            )
            finalTeam.addAll(
                arList.filter { !it.isPlayerLocked }.asSequence().shuffled()
                    .take(selectedCombination.ar - arListFixed)
            )
            teamCount = finalTeam.filter { it.teamName == batList[0].teamName }.count()

            val finalTeamDeepCopy = finalTeam.map { it.copy() }

            //Select a captain
            val lockedCap = finalTeamDeepCopy.filter { it.isCvcLocked }.randomOrNull()
            if (lockedCap != null) lockedCap.isCaptain = true
            else{
                val cap = finalTeamDeepCopy.randomOrNull()
                if(cap != null) cap.isCaptain = true
            }
            //Select a vice Cap
            val lockedViceCap = finalTeamDeepCopy.filter { it.isCvcLocked && !it.isCaptain }.randomOrNull()
            if (lockedViceCap != null) lockedViceCap.isViceCaptain = true
            else{
                val cap = finalTeamDeepCopy.filter { !it.isCaptain }.randomOrNull()
                if(cap != null) cap.isViceCaptain = true
            }

            if ((teamCount in 4..7) &&
                (finalTeamDeepCopy.sumOf { (it.playerSalary).toDouble() } <= 100) && !totalTeams.contains(
                    finalTeamDeepCopy
                )
            ) {
                totalTeams.add(finalTeamDeepCopy.toList())//
                duplicateTeams = 0
            }
            finalTeam.clear()
            duplicateTeams++
            println("duplicate--> $duplicateTeams")
        }
        return totalTeams
    }

}