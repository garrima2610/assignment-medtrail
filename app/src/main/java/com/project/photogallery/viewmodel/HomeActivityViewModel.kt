package com.project.photogallery.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.photogallery.services.PhotosRepository
import com.project.photogallery.services.model.DataWrapper
import com.project.photogallery.services.model.PhotosResult
import com.project.photogallery.services.model.Size

class HomeActivityViewModel(private val photosRepository: PhotosRepository) : ViewModel() {

    private var liveData: MutableLiveData<DataWrapper<PhotosResult>>? = null

    private var liveDataPhotoResult: MutableLiveData<List<Size>>? = null

    fun fetchPhotos() {
        if (liveData == null)
            liveData = photosRepository.fetchPhotos()
    }

    fun getPhotos(): LiveData<DataWrapper<PhotosResult>>? {
        return liveData
    }

    fun fetchPhotoDetails(id: String) {
        liveDataPhotoResult = photosRepository.fetchPhotosById(id)
    }

    fun getPhotoSizes(): LiveData<List<Size>>? {
        return liveDataPhotoResult
    }

    override fun onCleared() {
        super.onCleared()
        photosRepository.onDispose()
    }
}