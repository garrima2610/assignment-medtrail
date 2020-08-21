package com.incred.photogallery.di.modules

import com.incred.photogallery.di.scopes.ActivityScope
import com.incred.photogallery.services.PhotosRepository
import com.incred.photogallery.viewmodel.HomeActivityViewModelFactory
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