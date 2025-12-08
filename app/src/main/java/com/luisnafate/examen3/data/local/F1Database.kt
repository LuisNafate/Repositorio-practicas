package com.luisnafate.examen3.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.luisnafate.examen3.data.local.dao.FavoriteDriverDao
import com.luisnafate.examen3.data.local.dao.FavoriteRaceDao
import com.luisnafate.examen3.data.local.entity.FavoriteDriverEntity
import com.luisnafate.examen3.data.local.entity.FavoriteRaceEntity

@Database(
    entities = [FavoriteRaceEntity::class, FavoriteDriverEntity::class],
    version = 1,
    exportSchema = false
)
abstract class F1Database : RoomDatabase() {
    
    abstract fun favoriteRaceDao(): FavoriteRaceDao
    abstract fun favoriteDriverDao(): FavoriteDriverDao
    
    companion object {
        @Volatile
        private var INSTANCE: F1Database? = null
        
        fun getDatabase(context: Context): F1Database {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    F1Database::class.java,
                    "f1_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
