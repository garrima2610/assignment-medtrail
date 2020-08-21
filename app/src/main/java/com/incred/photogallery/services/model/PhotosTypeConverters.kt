package com.incred.photogallery.services.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PhotosTypeConverters {
    internal var gson = Gson()

    @TypeConverter
    fun stringToSomeObjectList(data: String?): List<Photo> {
        if (data == null) {
            return emptyList()
        }

        val listType = object : TypeToken<List<Photo>>() {

        }.type

        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun someObjectListToString(someObjects: List<Photo>): String {
        return gson.toJson(someObjects)
    }
}