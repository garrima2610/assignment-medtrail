package com.project.photogallery.services.model

import com.google.gson.annotations.SerializedName

class ErrorObject(@SerializedName("error") val error: String?) {
}