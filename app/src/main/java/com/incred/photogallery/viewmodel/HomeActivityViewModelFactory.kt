package com.incred.photogallery.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.incred.photogallery.services.PhotosRepository

class HomeActivityViewModelFactory(
    private val photosRepository: PhotosRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeActivityViewModel(photosRepository) as T
    }
}