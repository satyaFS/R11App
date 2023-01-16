package com.explore.r11.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.explore.r11.data.local.CricketDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesFirebaseFirestoreObj(): FirebaseFirestore = Firebase.firestore

    @Provides
    @Singleton
    fun provideCricketDatabase(app:Application):CricketDatabase{
        return Room.databaseBuilder(
            app,
            CricketDatabase::class.java,
            "Cricket.db"
        ).build()
    }
}