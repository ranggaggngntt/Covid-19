package com.example.covid_19.Repo
//
//import androidx.lifecycle.LiveData
//import com.example.covid_19.dao.ProvinceDao
//import com.example.covid_19.db.entity.ProvinceModel
//
//class ProvinceRepository(private val provinceDao: ProvinceDao) {
//
//    val allProvinceData: LiveData<List<ProvinceModel>> = provinceDao.getprovince()
//
//    suspend fun insert(province: ProvinceModel) {
//        provinceDao.insert(province)
//    }
//
//    suspend fun insertAll(provinces: List<ProvinceModel>) {
//        provinceDao.insertAll(provinces)
//    }
//
//    suspend fun deleteAll() {
//        provinceDao.deleteAll()
//    }
//
//    suspend fun update(province: ProvinceModel) {
//        provinceDao.update(province)
//    }
//
//    suspend fun delete(province: ProvinceModel) {
//        provinceDao.delete(province)
//    }
//}
