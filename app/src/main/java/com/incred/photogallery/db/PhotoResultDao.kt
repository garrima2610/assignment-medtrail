package com.incred.photogallery.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.incred.photogallery.services.model.Photos
import com.incred.photogallery.services.model.Size
import io.reactivex.Completable
import io.reactivex.Maybe

@Dao
interface PhotoResultDao {

    @Insert
    fun savePhotoSizeResults(photoSizeObject: Size): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun savePhotoResults(photo: Photos): Completable

    @Query("SELECT * FROM Size WHERE photoId =:id")
    fun fetchPhotoById(id: String): Maybe<List<Size>>

    @Query("SELECT * FROM Photos")
    fun fetchAllPhotos(): Maybe<List<Photos>>
}