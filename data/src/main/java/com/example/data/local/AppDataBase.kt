package com.example.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.local.dao.CoinDao
import com.example.data.local.entity.CoinEntity
import com.example.data.remote.dto.CoinDto

@Database(
    entities = [
        CoinEntity::class
    ], version = 2
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun coinDao(): CoinDao
}