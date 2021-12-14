package com.example.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.remote.dto.CoinDto
import com.example.domain.model.Coin

@Entity
data class CoinEntity(
    @PrimaryKey
    val pid:Int?=null,
    val id: String,
    val isActive: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
)

fun CoinEntity.toCoin(): Coin {

    return Coin(
        id = id,
        isActive = isActive,
        name = name,
        rank = rank,
        symbol = symbol
    )
}