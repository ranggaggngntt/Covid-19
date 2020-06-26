package com.example.covid_19.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.covid_19.Repo.ProvinceRepository
import com.example.covid_19.db.AppDatabase
import com.example.covid_19.db.entity.ProvinceModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProvinceViewModel() : ViewModel() {

    lateinit var repository: ProvinceRepository

    lateinit var allProvincesData: LiveData<List<ProvinceModel>>

    fun init(context: Context) {
        val provinceDao = AppDatabase.getDatabase(context).provinceDao()
        repository = ProvinceRepository(provinceDao)
        allProvincesData = repository.allProvinceData
    }

    fun delete(province: ProvinceModel) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(province)
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insertAll(provinces: List<ProvinceModel>) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAll()
        repository.insertAll(provinces)
    }
}
