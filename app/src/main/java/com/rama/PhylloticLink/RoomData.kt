package com.rama.PhylloticLink

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rama.PhylloticLink.data.ApodEntities
import com.rama.PhylloticLink.data.FavItems
import com.rama.PhylloticLink.data.ItemsDao
import com.rama.PhylloticLink.data.MarsEntities

@Database(entities = [(ApodEntities::class), (MarsEntities::class), (FavItems::class)], version = 1, exportSchema = false)
abstract class RoomData: RoomDatabase() {

    abstract fun itemsDao(): ItemsDao

    companion object {
        @Volatile
        private var INSTANCE: RoomData? = null
        fun getDatabase(context: Context): RoomData {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RoomData::class.java,
                    "tablas"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

}