package com.project.photogallery

import android.app.Application
import com.project.photogallery.db.PhotosResultDatabase
import com.project.photogallery.di.components.ApplicationComponent
import com.project.photogallery.di.components.DaggerApplicationComponent
import com.project.photogallery.network.ApiInterface
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