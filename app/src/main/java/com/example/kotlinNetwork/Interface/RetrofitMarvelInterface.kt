package com.example.kotlinNetwork.Interface

import com.example.kotlinNetwork.Model.MarvelResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitMarvelInterface {
    @GET("characters")
    fun getCharacterList(
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String
    ): Call<MarvelResponse>
}