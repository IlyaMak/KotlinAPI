package com.example.kotlinNetwork.Common

import com.example.kotlinNetwork.Interface.RetrofitMarvelInterface
import com.example.kotlinNetwork.Retrofit.RetrofitMarvelClient

object Marvel {
    private const val BASE_MARVEL_URL = "https://gateway.marvel.com/v1/public/"

    val retrofitMarvelService: RetrofitMarvelInterface
        get() = RetrofitMarvelClient.getClient(BASE_MARVEL_URL).create(RetrofitMarvelInterface::class.java)
}