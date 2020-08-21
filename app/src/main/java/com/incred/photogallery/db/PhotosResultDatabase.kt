package com.incred.photogallery.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.incred.photogallery.services.model.Photos
import com.incred.photogallery.services.model.PhotosTypeConverters
import com.incred.photogallery.services.model.Size

@Database(entities = [Size::class, Photos::class], version = 1, exportSchema = false)
@TypeConverters(PhotosTypeConverters::class)
abstract class PhotosResultDatabase : RoomDatabase() {

    abstract fun photoDaoAccess(): PhotoResultDao
}