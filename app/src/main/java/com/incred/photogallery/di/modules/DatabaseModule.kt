package com.incred.photogallery.di.modules

import androidx.room.Room
import com.incred.photogallery.PhotosApplication
import com.incred.photogallery.db.PhotosResultDatabase
import com.incred.photogallery.di.scopes.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {

    private val DB_NAME = "db_photo_sizes"

    @ApplicationScope
    @Provides
    fun provideDataBase(): PhotosResultDatabase {
        return Room.databaseBuilder(
            PhotosApplication.getApp(),
            PhotosResultDatabase::class.java,
            DB_NAME
        ).allowMainThreadQueries().build()
    }
}