package com.incred.photogallery.services.model

import com.google.gson.annotations.SerializedName

class ErrorObject(@SerializedName("error") val error: String?) {
}