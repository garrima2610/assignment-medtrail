package com.incred.photogallery.services.model


import com.google.gson.annotations.SerializedName

data class PhotosResult(
    @SerializedName("photos")
    val photos: Photos,
    @SerializedName("stat")
    val stat: String
)