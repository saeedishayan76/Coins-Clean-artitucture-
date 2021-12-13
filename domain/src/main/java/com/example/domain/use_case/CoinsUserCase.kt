package com.example.domain.use_case

import com.example.common.vo.Resource
import com.example.domain.model.Coin
import com.example.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class CoinsUserCase @Inject constructor(
    private val coinRepository: CoinRepository
) {
    operator fun invoke():Flow<Resource<List<Coin>>> {
        return coinRepository.getCoin()
    }


}