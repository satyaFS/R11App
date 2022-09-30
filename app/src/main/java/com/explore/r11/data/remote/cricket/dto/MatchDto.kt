package com.explore.r11.data.remote.cricket.dto

data class MatchDto(
    var league: String?="",
    var teamOne: String?="",
    var teamTwo:String?="",
    var teamOneSC:String?="",
    var teamTwoSC:String?="",
    var startTime:String?= "",
    var matchID:String?= ""
)