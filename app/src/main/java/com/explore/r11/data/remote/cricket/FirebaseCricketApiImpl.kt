package com.explore.r11.data.remote.cricket

import com.explore.r11.data.remote.cricket.dto.MatchDto
import com.google.firebase.firestore.FirebaseFirestore

class FirebaseCricketApiImpl constructor(
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

        return matches
    }
}


