package com.incred.photogallery.services.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Photos(

    @PrimaryKey
    @SerializedName("page")
    val page: Int,
    @SerializedName("pages")
    val pages: Int,
    @SerializedName("perpage")
    val perpage: Int,


    @SerializedName("photo")
    val photo: List<Photo>,

    @SerializedName("total")
    val total: String
)