package com.project.photogallery.di.components

import com.project.photogallery.di.modules.HomeAdapterModule
import com.project.photogallery.di.modules.HomeViewModelFactoryModule
import com.project.photogallery.di.scopes.ActivityScope
import com.project.photogallery.view.home.HomeActivity
import dagger.Component

@ActivityScope
@Component(modules = [HomeAdapterModule::class, HomeViewModelFactoryModule::class])
interface HomeActivityComponents {
    fun inject(activity: HomeActivity)

}