package com.incred.photogallery.di.modules

import com.incred.photogallery.di.scopes.ActivityScope
import com.incred.photogallery.view.home.HomeActivity
import com.incred.photogallery.viewmodel.HomeActivityViewModel
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