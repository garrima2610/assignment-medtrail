package com.project.photogallery.di.modules

import com.project.photogallery.di.scopes.ActivityScope
import com.project.photogallery.view.home.HomeActivity
import dagger.Module
import dagger.Provides

@Module
class HomeActivityContextModule(
    private val activity: HomeActivity
) {

    @Provides
    @ActivityScope
    fun provideHomeActivity(): HomeActivity {
        return activity
    }


}