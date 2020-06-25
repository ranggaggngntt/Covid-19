package com.example.covid_19.model


import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(tableName = "province")
data class kawalcoronaItem(
    @SerializedName("attributes")
    val attributes: Attributes
)