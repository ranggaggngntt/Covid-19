package com.example.covid_19.dao

//import androidx.lifecycle.LiveData
//import androidx.room.*
//import com.example.covid_19.db.entity.ProvinceModel
//
//@Dao
//interface ProvinceDao {
//
//    @Query("SELECT * from province")
//    fun getprovince(): LiveData<List<ProvinceModel>>
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insert(province: ProvinceModel)
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertAll(provinces: List<ProvinceModel>)
//
//    @Update(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun update(province: ProvinceModel)
//
//    @Delete()
//    suspend fun delete(province: ProvinceModel)
//
//    @Query("DELETE FROM province")
//    suspend fun deleteAll()
//}