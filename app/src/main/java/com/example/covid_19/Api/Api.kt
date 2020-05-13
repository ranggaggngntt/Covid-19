package com.example.covid_19.Api

import retrofit2.http.GET

interface Api {

    @GET("country")
    fun getData()
}