package com.example.covid_19.model


import com.google.gson.annotations.SerializedName

class kawalcorona : ArrayList<kawalcoronaItem>()

data class kawalcoronaItem(
    @SerializedName("attributes")
    var attributes: Attributes
)

data class Attributes(
    @SerializedName("FID")
    var fID: Int,
    @SerializedName("Kasus_Meni")
    var kasusMeni: Int,
    @SerializedName("Kasus_Posi")
    var kasusPosi: Int,
    @SerializedName("Kasus_Semb")
    var kasusSemb: Int,
    @SerializedName("Kode_Provi")
    var kodeProvi: Int,
    @SerializedName("Provinsi")
    var provinsi: String
)