package com.example.covid_19.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.covid_19.model.kawalcoronaCountryItem

interface CountryDao {
    @Query("select * from country_item")

    fun getCountryData(): LiveData<List<kawalcoronaCountryItem>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(countryData: kawalcoronaCountryItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(countryDatas: List<kawalcoronaCountryItem>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(countryData: kawalcoronaCountryItem)

    @Delete()
    suspend fun delete(countryData: kawalcoronaCountryItem)

    @Query("DELETE FROM country_item")
    suspend fun deleteAll()

}