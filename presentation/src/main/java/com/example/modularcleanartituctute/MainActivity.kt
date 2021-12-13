package com.example.modularcleanartituctute

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.common.vo.Resource
import com.example.modularcleanartituctute.viewModel.CoinsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private  val TAG = "MainActivity"
    private val coinsViewModel by viewModels<CoinsViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        coinsViewModel.coins.observe(this){
            when(it)
            {
                is Resource.Loading ->{
                    Log.i(TAG, "onCreate: Loading")
                }
                is Resource.Error ->{
                    Log.i(TAG, "onCreate: ${it.message}")
                }
                is Resource.Success ->{
                    Log.i(TAG, "onCreate: ${it.data?.size}")
                }
            }
        }
    }
}