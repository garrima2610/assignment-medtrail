package com.incred.photogallery.view

import com.incred.photogallery.services.model.Photo

interface OnListItemClick {

    fun onListItemClicked(photo: Photo?)
}