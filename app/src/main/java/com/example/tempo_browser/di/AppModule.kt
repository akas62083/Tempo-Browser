package com.example.tempo_browser.di

import android.content.Context
import androidx.room.Room
import com.example.tempo_browser.BookmarkRepository
import com.example.tempo_browser.db.AppDatabase
import com.example.tempo_browser.db.BookmarkDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "bookmark_db"
        ).fallbackToDestructiveMigration().build()
    @Provides
    fun provideBookmarkDao(database: AppDatabase): BookmarkDao = database.getBookmarkDao()

    @Provides
    @Singleton
    fun provideBookmarkRepository(bookmarkDao: BookmarkDao): BookmarkRepository =
        BookmarkRepository(bookmarkDao)
}