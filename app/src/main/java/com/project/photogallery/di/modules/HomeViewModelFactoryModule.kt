package com.project.photogallery.di.modules

import com.project.photogallery.di.scopes.ActivityScope
import com.project.photogallery.services.PhotosRepository
import com.project.photogallery.viewmodel.HomeActivityViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class HomeViewModelFactoryModule {

    @ActivityScope
    @Provides
    fun provideHomeViewModelFactory(photosRepository: PhotosRepository): HomeActivityViewModelFactory {
        return HomeActivityViewModelFactory(photosRepository)
    }
}