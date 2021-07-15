package com.aws.dagger2.di.main

import com.aws.dagger2.network.main.MainApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
object MainModule {

    @Provides
    fun provideMainApi(retrofit: Retrofit): MainApi {
        return retrofit.create(MainApi::class.java)
    }

}