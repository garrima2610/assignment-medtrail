package com.project.photogallery.di.components

import com.project.photogallery.PhotosApplication
import com.project.photogallery.di.modules.DatabaseModule
import com.project.photogallery.di.modules.RetrofitModule
import com.project.photogallery.di.scopes.ApplicationScope
import dagger.Component


@ApplicationScope
@Component(modules = [RetrofitModule::class, DatabaseModule::class])
interface ApplicationComponent {

    fun injectApplication(myApplication: PhotosApplication)
}