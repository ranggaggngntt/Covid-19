package com.example.covid_19.Api

import com.example.covid_19.model.kawalcoronaItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("indonesia/provinsi")
    fun getData(): Call<List<kawalcoronaItem>>

    @GET("indonesia/provinsi/{FID}")
    fun getDetailData(
        @Path("FID") FID: Int
    ): Call<kawalcoronaItem>
}