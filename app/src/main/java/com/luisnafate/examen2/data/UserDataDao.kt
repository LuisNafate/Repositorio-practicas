package com.luisnafate.examen2.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDataDao {
    @Query("SELECT * FROM user_data ORDER BY id DESC LIMIT 1")
    fun getUserData(): Flow<UserData?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserData(userData: UserData)


    @Query("DELETE FROM user_data")
    suspend fun deleteAll()
}

