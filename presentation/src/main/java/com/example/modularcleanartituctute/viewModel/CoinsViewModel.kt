package com.example.modularcleanartituctute.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.vo.Resource
import com.example.domain.model.Coin
import com.example.domain.use_case.CoinsUserCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class CoinsViewModel @Inject constructor(
    private val getCoinsUserCase: CoinsUserCase
) :ViewModel() {

    val coins = MutableLiveData<Resource<List<Coin>>>()
    init {
        getCoins()
    }
    private fun getCoins()
    {
        getCoinsUserCase().map {
            when(it)
            {
                is Resource.Loading ->{
                    coins.value = Resource.Loading()
                }
                is Resource.Error ->{
                    coins.value = Resource.Error(it.message?:"error")
                }
                is Resource.Success ->{
                    coins.value = Resource.Success(it.data!!)
                }
            }
        }.launchIn(viewModelScope)
    }
}