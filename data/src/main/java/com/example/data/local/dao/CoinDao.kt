package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.local.entity.CoinEntity
import com.example.domain.model.Coin
import kotlinx.coroutines.flow.Flow

@Dao
interface CoinDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoins(coins: List<CoinEntity>)

    @Query("SELECT * FROM coinentity")
    suspend fun getCoins(): List<CoinEntity>

    @Query("DELETE FROM coinentity")
    suspend fun deleteAllCoins()
}