package com.example.modularcleanartituctute.di

import android.content.Context
import androidx.room.Room
import com.example.data.local.AppDataBase
import com.example.data.local.dao.CoinDao
import com.example.data.remote.ApiService
import com.example.data.repository.CoinRepositoryImpl
import com.example.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl("https://api.coinpaprika.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRoom(@ApplicationContext app: Context): AppDataBase {
        return Room.databaseBuilder(app, AppDataBase::class.java, "coin_db")
            .fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideCoinDao(appDataBase: AppDataBase):CoinDao{
        return appDataBase.coinDao()
    }


    @Provides
    fun provideCoinRepo(apiService: ApiService,coinDao: CoinDao): CoinRepository {
        return CoinRepositoryImpl(apiService,coinDao)
    }


}