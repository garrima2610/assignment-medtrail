package com.incred.photogallery.services.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Size(

    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @SerializedName("photoId")
    var photoId: String,

    @SerializedName("height")
    val height: String,

    @SerializedName("label")
    val label: String,

    @SerializedName("media")
    val media: String,

    @SerializedName("source")
    val source: String,

    @SerializedName("url")
    val url: String,

    @SerializedName("width")
    val width: String
)