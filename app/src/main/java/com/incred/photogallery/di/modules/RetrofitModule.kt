package com.incred.photogallery.di.modules

import com.incred.photogallery.di.scopes.ApplicationScope
import com.incred.photogallery.network.ApiInterface
import com.incred.photogallery.utility.Constants
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


@Module
class RetrofitModule {

    @ApplicationScope
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .build()
    }


    @ApplicationScope
    @Provides
    fun getApiInterface(retroFit: Retrofit): ApiInterface {
        return retroFit.create(ApiInterface::class.java)
    }

}