package com.example.covid_19.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "province")
data class ProvinceModel(
    @PrimaryKey var FID: Int,
    var kasusMeni: Int,
    var kasusPosi: Int,
    var kasusSemb: Int,
    var kodeProvi: Int,
    var provinsi: String
){
    constructor() : this(0,0,0,0,0,"")
}