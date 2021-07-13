package com.aws.dagger2.di.auth

import com.aws.dagger2.network.auth.AuthApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
object AuthModule {

    @Provides
    fun provideAuthAPI(retrofit: Retrofit): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }

}