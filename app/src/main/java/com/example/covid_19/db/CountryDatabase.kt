package com.example.covid_19.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.covid_19.dao.CountryDao
import com.example.covid_19.model.kawalcoronaCountryItem
import com.example.covid_19.model.kawalcoronaItem

@Database(entities = arrayOf(kawalcoronaCountryItem::class), version = 1, exportSchema = false)

public abstract class CountryDatabase : RoomDatabase() {
    abstract fun countryDao(): CountryDao

    companion object {
        @Volatile
        private var INSTANCE: CountryDatabase? = null

        fun getDatabase(context: Context): CountryDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,CountryDatabase::class.java,
                    "country_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}