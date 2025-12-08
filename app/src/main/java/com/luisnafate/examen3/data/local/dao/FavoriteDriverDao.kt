package com.luisnafate.examen3.data.local.dao

import androidx.room.*
import com.luisnafate.examen3.data.local.entity.FavoriteDriverEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDriverDao {
    
    @Query("SELECT * FROM favorite_drivers ORDER BY addedAt DESC")
    fun getAllFavoriteDrivers(): Flow<List<FavoriteDriverEntity>>
    
    @Query("SELECT * FROM favorite_drivers WHERE driverId = :driverId")
    suspend fun getFavoriteDriverById(driverId: String): FavoriteDriverEntity?
    
    @Query("SELECT EXISTS(SELECT 1 FROM favorite_drivers WHERE driverId = :driverId)")
    suspend fun isFavorite(driverId: String): Boolean
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteDriver(driver: FavoriteDriverEntity)
    
    @Delete
    suspend fun deleteFavoriteDriver(driver: FavoriteDriverEntity)
    
    @Query("DELETE FROM favorite_drivers WHERE driverId = :driverId")
    suspend fun deleteFavoriteDriverById(driverId: String)
}
