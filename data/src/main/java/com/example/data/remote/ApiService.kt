package com.example.data.remote

import com.example.data.remote.dto.CoinDto
import retrofit2.http.GET

interface ApiService {


    @GET("v1/coins")
    suspend fun getCoins():List<CoinDto>
}