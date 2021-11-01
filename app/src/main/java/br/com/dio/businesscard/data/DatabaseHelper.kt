package br.com.dio.businesscard.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BusinessCard::class], version = 1)
abstract class DatabaseHelper : RoomDatabase() {


    abstract fun businessDao(): BusinessCardDao

    companion object {
        @Volatile
        private var instance: DatabaseHelper? = null

        fun getDatabase(context: Context): DatabaseHelper {
            val liveInstance = instance
            if (liveInstance != null) {
                return liveInstance
            }

            synchronized(this) {
                val buildingInstance = Room.databaseBuilder(
                    context.applicationContext,
                    DatabaseHelper::class.java,
                    "app_database"
                ).build()
                instance = buildingInstance
                return buildingInstance
            }
        }
    }
}