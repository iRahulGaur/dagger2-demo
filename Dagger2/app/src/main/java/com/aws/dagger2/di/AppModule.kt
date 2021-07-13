package com.aws.dagger2.di

import android.app.Application
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.aws.dagger2.R
import com.aws.dagger2.util.Constants
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideRequestOptions(): RequestOptions {
        return RequestOptions()
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.placeholder)
    }

    @Singleton
    @Provides
    fun profileGlideInstance(application: Application, requestOptions: RequestOptions) : RequestManager {
        return Glide.with(application)
            .setDefaultRequestOptions(requestOptions)
    }

    @Singleton
    @Provides
    fun provideAppLogo(application: Application): Drawable {
        return ContextCompat.getDrawable(application, R.drawable.logo)!!
    }

}