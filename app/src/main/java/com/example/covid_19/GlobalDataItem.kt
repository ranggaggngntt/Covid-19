package com.example.covid_19

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class GlobalDataItem (
    @SerializedName("confirmed")
    val confirmed: String,
    @SerializedName("recovered")
    val recovered: String,
    @SerializedName("deaths")
    val deaths: String,
    @SerializedName("countryDetail")
    val countryDetail: String
) : Serializable