package com.soccer.results.di

import android.content.Context
import androidx.room.Room
import com.soccer.results.local.AppDatabase
import com.soccer.results.local.SoccerResultDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient

@Module
@InstallIn(SingletonComponent::class)
object DBModule {

    @Provides
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java, "SoccerResultsDB"
        ).build()
    }

    @Provides
    fun provideMovieDao(appDatabase: AppDatabase): SoccerResultDao {
        return appDatabase.soccerResultDao()
    }
}