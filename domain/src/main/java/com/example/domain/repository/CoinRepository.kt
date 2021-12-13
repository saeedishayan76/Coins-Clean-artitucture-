package com.example.domain.repository

import com.example.common.vo.Resource
import com.example.domain.model.Coin
import kotlinx.coroutines.flow.Flow

interface CoinRepository{

     fun getCoin():Flow<Resource<List<Coin>>>
}