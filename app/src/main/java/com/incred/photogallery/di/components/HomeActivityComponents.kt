package com.incred.photogallery.di.components

import com.incred.photogallery.di.modules.HomeAdapterModule
import com.incred.photogallery.di.modules.HomeViewModelFactoryModule
import com.incred.photogallery.di.scopes.ActivityScope
import com.incred.photogallery.view.home.HomeActivity
import dagger.Component

@ActivityScope
@Component(modules = [HomeAdapterModule::class, HomeViewModelFactoryModule::class])
interface HomeActivityComponents {
    fun inject(activity: HomeActivity)

}