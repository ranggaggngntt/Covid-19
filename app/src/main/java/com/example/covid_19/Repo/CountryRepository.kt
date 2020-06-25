package com.example.covid_19.Repo

import androidx.lifecycle.LiveData
import com.example.covid_19.dao.CountryDao
import com.example.covid_19.model.kawalcoronaCountryItem
import com.example.covid_19.model.kawalcoronaItem

class CountryRepository(private val countryDao: CountryDao){
    val country: LiveData<List<kawalcoronaCountryItem>> = countryDao.getCountryData()

    suspend fun insert(countryData: kawalcoronaCountryItem) {
        countryDao.insert(countryData)
    }

    suspend fun insertAll(countryDatas: List<kawalcoronaCountryItem>) {
        countryDao.insertAll(countryDatas)
    }

    suspend fun deleteAll() {
        countryDao.deleteAll()
    }

    suspend fun update(countryData: kawalcoronaCountryItem) {
        countryDao.update(countryData)
    }

    suspend fun delete(countryData: kawalcoronaCountryItem) {
        countryDao.delete(countryData)
    }

}