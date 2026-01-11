package com.example.tempo_browser

import com.example.tempo_browser.db.BookmarkDao
import jakarta.inject.Inject

class BookmarkRepository @Inject constructor(
    private val bookmarkDao: BookmarkDao
) {

}