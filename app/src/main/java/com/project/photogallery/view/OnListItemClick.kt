package com.project.photogallery.view

import com.project.photogallery.services.model.Photo

interface OnListItemClick {

    fun onListItemClicked(photo: Photo?)
}