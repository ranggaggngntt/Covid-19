package com.example.covid_19.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.covid_19.dao.ProvinceDao
import com.example.covid_19.db.entity.ProvinceModel

@Database(entities = arrayOf(ProvinceModel::class), version = 3, exportSchema = false)
 abstract class AppDatabase : RoomDatabase(), ProvinceDao {

    abstract fun provinceDao(): AppDatabase

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "content_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
