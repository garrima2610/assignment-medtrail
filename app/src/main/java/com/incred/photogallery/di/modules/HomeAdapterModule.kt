package com.incred.photogallery.di.modules

import com.incred.photogallery.di.scopes.ActivityScope
import com.incred.photogallery.view.home.HomeActivity
import com.incred.photogallery.view.home.HomeAdapter
import com.incred.photogallery.viewmodel.HomeActivityViewModel
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