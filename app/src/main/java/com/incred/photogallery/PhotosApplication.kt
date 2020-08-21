package com.incred.photogallery

import android.app.Application
import com.incred.photogallery.db.PhotosResultDatabase
import com.incred.photogallery.di.components.ApplicationComponent
import com.incred.photogallery.di.components.DaggerApplicationComponent
import com.incred.photogallery.network.ApiInterface
import javax.inject.Inject

class PhotosApplication : Application() {

    private var applicationComponent: ApplicationComponent? = null

    @Inject
    lateinit var apiInterface: ApiInterface

    @Inject
    lateinit var database: PhotosResultDatabase

    override fun onCreate() {
        super.onCreate()
        application = this
        applicationComponent = DaggerApplicationComponent.create()
        applicationComponent?.injectApplication(this)
    }

    companion object {
         private lateinit var application: PhotosApplication
        fun getApp(): PhotosApplication {
            return application
        }
    }
}