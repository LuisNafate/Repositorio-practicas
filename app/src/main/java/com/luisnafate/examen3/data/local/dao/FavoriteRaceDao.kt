package com.luisnafate.examen3.data.local.dao

import androidx.room.*
import com.luisnafate.examen3.data.local.entity.FavoriteRaceEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteRaceDao {
    
    @Query("SELECT * FROM favorite_races ORDER BY addedAt DESC")
    fun getAllFavoriteRaces(): Flow<List<FavoriteRaceEntity>>
    
    @Query("SELECT * FROM favorite_races WHERE uniqueId = :uniqueId")
    suspend fun getFavoriteRaceById(uniqueId: String): FavoriteRaceEntity?
    
    @Query("SELECT EXISTS(SELECT 1 FROM favorite_races WHERE uniqueId = :uniqueId)")
    suspend fun isFavorite(uniqueId: String): Boolean
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteRace(race: FavoriteRaceEntity)
    
    @Delete
    suspend fun deleteFavoriteRace(race: FavoriteRaceEntity)
    
    @Query("DELETE FROM favorite_races WHERE uniqueId = :uniqueId")
    suspend fun deleteFavoriteRaceById(uniqueId: String)
}
