package com.example.covid_19.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class AttributesX(
    @SerializedName("Active")
    val active: Int,
    @SerializedName("Confirmed")
    val confirmed: Int,
    @SerializedName("Country_Region")
    val countryRegion: String,
    @SerializedName("Deaths")
    val deaths: Int,
    @SerializedName("Last_Update")
    val lastUpdate: Int,
    @SerializedName("Lat")
    val lat: Int,
    @SerializedName("Long_")
    val long: Int,
    @SerializedName("OBJECTID")
    val oBJECTID: Int,
    @SerializedName("Recovered")
    val recovered: Int

)