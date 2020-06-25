package com.example.covid_19.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.covid_19.Repo.CountryRepository
import com.example.covid_19.db.CountryDatabase
import com.example.covid_19.model.kawalcoronaCountryItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CountryViewModel(): ViewModel() {
    lateinit var repository: CountryRepository

    lateinit var countrys: LiveData<List<kawalcoronaCountryItem>>

    fun init(context: Context) {
        val countryDao = CountryDatabase.getDatabase(context).countryDao()
        repository = CountryRepository(countryDao)
        countrys = repository.country
    }

    fun delete(country: kawalcoronaCountryItem) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(country)
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insertAll(country: List<kawalcoronaCountryItem>) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAll()
        repository.insertAll(country)
    }

}