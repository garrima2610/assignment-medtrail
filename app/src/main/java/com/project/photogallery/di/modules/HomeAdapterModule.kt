package com.project.photogallery.di.modules

import com.project.photogallery.di.scopes.ActivityScope
import com.project.photogallery.view.home.HomeActivity
import com.project.photogallery.view.home.HomeAdapter
import dagger.Module
import dagger.Provides

@Module(includes = [HomeActivityContextModule::class])
class HomeAdapterModule {

    @Provides
    @ActivityScope
    fun provideAdapter(
        mainActivity: HomeActivity
    ): HomeAdapter {
        return HomeAdapter(mainActivity)
    }


}