package com.example.covid_19.Api

import com.example.covid_19.model.Attributes
import com.example.covid_19.model.kawalcorona
import retrofit2.Call
import retrofit2.http.GET

interface Api {

    @GET("indonesia/provinsi")
    fun getData(): Call<List<Attributes>>
}