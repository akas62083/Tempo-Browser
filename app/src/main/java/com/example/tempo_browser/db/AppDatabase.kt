package com.example.tempo_browser.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [BookmarkEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getBookmarkDao(): BookmarkDao
}