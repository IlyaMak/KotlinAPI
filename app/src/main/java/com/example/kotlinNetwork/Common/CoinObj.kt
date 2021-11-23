package com.example.kotlinNetwork.Common

import com.example.kotlinNetwork.Interface.RetrofitInterface
import com.example.kotlinNetwork.Retrofit.RetrofitClient

object CoinObj {
    private val BASE_URL2 = "https://api.coincap.io/v2/"
    val retrofitService: RetrofitInterface
        get() = RetrofitClient.getClient(BASE_URL2).create(RetrofitInterface::class.java)
}