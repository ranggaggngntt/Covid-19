package com.example.covid_19.model


import androidx.room.Embedded
import androidx.room.Entity
import com.google.gson.annotations.SerializedName

data class kawalcoronaCountryItem(
    @SerializedName("attributes")
    val attributesX: AttributesX
)