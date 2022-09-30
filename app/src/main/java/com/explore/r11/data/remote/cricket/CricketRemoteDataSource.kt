package com.explore.r11.data.remote.cricket

import kotlinx.coroutines.CoroutineDispatcher

class CricketRemoteDataSource constructor(
    private val cricketApi: CricketApi,
    private val ioDispatcher: CoroutineDispatcher
) {

}