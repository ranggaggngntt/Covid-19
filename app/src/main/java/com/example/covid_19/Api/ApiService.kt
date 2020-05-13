package com.example.covid_19.Api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object ApiService {

    var url = "https://api.kawalcorona.com"

    fun create(): Api{
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(url)
            .build()

        return retrofit.create(Api::class.java)
    }
}