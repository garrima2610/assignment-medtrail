package com.incred.photogallery.network

import com.incred.photogallery.services.model.PhotoSizesResult
import com.incred.photogallery.services.model.PhotosResult
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface ApiInterface {

    @GET("/services/rest/?method=flickr.people.getPublicPhotos")
    fun getPhotos(
        @Query("api_key") api_key: String,
        @Query("user_id") user_id: String,
        @Query("format") format: String,
        @Query("nojsoncallback") nojsoncallback: Int
    ): Single<PhotosResult>


    @GET("/services/rest/?method=flickr.photos.getSizes")
    fun fetchPhotoDetails(
        @Query("photo_id") photo_id:String,
        @Query("api_key") api_key:String,
        @Query("user_id") user_id: String,
        @Query("format") format: String,
        @Query("nojsoncallback") nojsoncallback: Int
    ):Single<PhotoSizesResult>
}