package com.explore.r11.di

import com.explore.r11.data.remote.cricket.CricketApi
import com.explore.r11.data.remote.cricket.FirebaseCricketApiImpl
import com.explore.r11.data.repository.CricketRepositoryImpl
import com.explore.r11.domain.repository.CricketRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule{

    @Binds
    @Singleton
    abstract fun bindCricketApi(
        cricketApiImpl: FirebaseCricketApiImpl
    ):CricketApi

    @Binds
    @Singleton
    abstract fun bindCricketRepository(
        cricketRepositoryImpl: CricketRepositoryImpl
    ):CricketRepository
}


















