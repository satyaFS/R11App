package com.explore.r11.data.remote.cricket

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.util.Log
import com.explore.r11.data.remote.cricket.dto.MatchDto
import com.explore.r11.data.remote.cricket.dto.PlayerDto
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseCricketApiImpl @Inject constructor(
    private val firebaseObj:FirebaseFirestore
): CricketApi {
    override suspend fun getMatches(): List<MatchDto> {
        val matchesRef = firebaseObj.collection("Matches")
        val matches = mutableListOf<MatchDto>()

        matchesRef.document("matches").get()
            .addOnSuccessListener { matchDoc ->
                val matchData = matchDoc.data
                val keys = matchData?.keys?.toList()
                keys?.forEach { k ->
                    val matchMap: HashMap<String, String>? =
                        matchData[k] as? HashMap<String, String>
                    matchMap?.let { matchMap ->
                        val match = MatchDto(
                            matchMap["league"],
                            matchMap["teamOne"],
                            matchMap["teamTwo"].toString(),
                            matchMap["teamOneSC"].toString(),
                            matchMap["teamTwoSC"].toString(),
                            matchMap["startTime"],
                            matchMap["matchID"]
                        )
                        matches.add(match)
                    }
                }
            }
            .addOnFailureListener{
                Log.d(ContentValues.TAG, "Error getting documents: ", it)
            }.await()
        return matches
    }

    override suspend fun getPlayers(matchId: Int): List<PlayerDto> {
        val matchesRef = firebaseObj.collection("Matches")
        val listOfPlayers = mutableListOf<PlayerDto>()
        println(matchId)
        matchesRef.document("matches").collection("Players").document(matchId.toString())
            .get().addOnSuccessListener {players->
                val playerData = players.data
                if(playerData != null) {
                    val playerKeys = playerData.keys
                    for(p in playerKeys)
                    {
                        val hashMap: Map<String, String> =
                            playerData[p] as HashMap<String, String>
                        listOfPlayers.add(
                            PlayerDto(
                                hashMap["name"] ,
                                hashMap["team"],
                                hashMap["type"],
                                hashMap["salary"],
                                hashMap["points"],
                            )
                        )
                    }
                }
            }
            .addOnFailureListener {
                Log.d(TAG, "Error getting documents: ", it)
            }
            .await()
        return  listOfPlayers
    }
}


