package com.example.data.repository

import com.example.common.vo.Resource
import com.example.data.local.dao.CoinDao
import com.example.data.local.entity.toCoin
import com.example.data.remote.ApiService
import com.example.data.remote.dto.toCoin
import com.example.data.remote.dto.toCoinEntity
import com.example.domain.model.Coin
import com.example.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val apiService: ApiService ,
    private val coinDao: CoinDao
) : CoinRepository {
    override fun getCoin(): Flow<Resource<List<Coin>>> =  flow {
        emit(Resource.Loading<List<Coin>>())
        val oldData = coinDao.getCoins()
        try {
            val coins = apiService.getCoins()
            coinDao.deleteAllCoins()
            coinDao.insertCoins(coins = coins.map{it.toCoinEntity()})
            } catch (e: HttpException) {
                emit(Resource.Error<List<Coin>>(e.message() ?: "unspexted data ..."))

            } catch (e: IOException) {
                emit(Resource.Error<List<Coin>>(e.localizedMessage ?: "server error  ..."))

            }

        val newCoins = coinDao.getCoins()
        emit(Resource.Success<List<Coin>>(newCoins.map { it.toCoin() }))

        }


    }
