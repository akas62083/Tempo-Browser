package com.example.tempo_browser.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmark")
data class BookmarkEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "bm_id")
    val id: Long = 0,
    @ColumnInfo(name = "bm_url")
    val url: String,
    @ColumnInfo(name = "bm_title")
    val title: String,
)
