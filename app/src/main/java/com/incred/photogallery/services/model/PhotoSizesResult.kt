package com.incred.photogallery.services.model


import com.google.gson.annotations.SerializedName

data class PhotoSizesResult(
    @SerializedName("sizes")
    val sizes: Sizes,
    @SerializedName("stat")
    val stat: String
)