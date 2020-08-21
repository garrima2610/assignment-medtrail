package com.incred.photogallery.di.components

import com.incred.photogallery.PhotosApplication
import com.incred.photogallery.di.modules.DatabaseModule
import com.incred.photogallery.di.modules.RetrofitModule
import com.incred.photogallery.di.scopes.ApplicationScope
import dagger.Component


@ApplicationScope
@Component(modules = [RetrofitModule::class, DatabaseModule::class])
interface ApplicationComponent {

    fun injectApplication(myApplication: PhotosApplication)
}