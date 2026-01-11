package com.example.tempo_browser.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarkDao {
    @Insert
    suspend fun insertBm(bookmark: BookmarkEntity)
    @Delete
    suspend fun deleteBm(bookmark: BookmarkEntity)
    @Update
    suspend fun updateBm(bookmark: BookmarkEntity)
    @Query("select * from bookmark where bm_id = :id")
    fun getBm(id: Long): Flow<BookmarkEntity>
    @Query("select * from bookmark order by bm_id asc")
    fun getAllBm(): Flow<List<BookmarkEntity>>
}