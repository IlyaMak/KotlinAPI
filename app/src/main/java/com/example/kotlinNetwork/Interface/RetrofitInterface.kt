package com.example.kotlinNetwork.Interface

import com.example.kotlinNetwork.Model.CoinList
import retrofit2.Call
import retrofit2.http.*

interface RetrofitInterface {
    @GET("assets")
    fun getCoinList(): Call<CoinList>
}