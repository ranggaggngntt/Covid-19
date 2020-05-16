package com.example.covid_19.KawalCoronaApi

import com.example.covid_19.model.kawalcoronaCountryItem
import com.example.covid_19.model.kawalcoronaItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface KawalCoronaApi {

    @GET("/")
    fun getCountry(): Call<List<kawalcoronaCountryItem>>

    @GET("/indonesia/provinsi")
    fun getData(): Call<List<kawalcoronaItem>>

    @GET("/indonesia/provinsi/{FID}")
    fun getDetailData(
        @Path("FID") FID: Int
    ): Call<kawalcoronaItem>
}