package com.example.data.repository

import com.example.common.vo.Resource
import com.example.data.remote.ApiService
import com.example.data.remote.dto.toCoin
import com.example.domain.model.Coin
import com.example.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : CoinRepository {
    override fun getCoin(): Flow<Resource<List<Coin>>> =  flow {
            try {
                emit(Resource.Loading<List<Coin>>())
                val coins = apiService.getCoins().map { it.toCoin() }
                emit(Resource.Success<List<Coin>>(coins))

            } catch (e: HttpException) {
                emit(Resource.Error<List<Coin>>(e.message() ?: "unspexted data ..."))

            } catch (e: IOException) {
                emit(Resource.Error<List<Coin>>(e.localizedMessage ?: "server error  ..."))

            }
        }
    }
