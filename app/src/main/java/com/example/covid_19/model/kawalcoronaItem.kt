package com.example.covid_19.model


import com.google.gson.annotations.SerializedName

data class kawalcoronaItem(
    @SerializedName("attributes")
    val attributes: Attributes
)