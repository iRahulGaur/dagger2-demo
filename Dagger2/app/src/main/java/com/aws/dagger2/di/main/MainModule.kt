package com.aws.dagger2.di.main

import com.aws.dagger2.network.main.MainApi
import com.aws.dagger2.ui.main.post.PostRecyclerAdapter
import com.aws.dagger2.util.VerticalSpacingItemDecoration
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
object MainModule {

    @Provides
    fun provideMainApi(retrofit: Retrofit): MainApi {
        return retrofit.create(MainApi::class.java)
    }

    @Provides
    fun providePostRecyclerAdapter(): PostRecyclerAdapter {
        return PostRecyclerAdapter()
    }

    @Provides
    fun provideVerticalSpacing(): VerticalSpacingItemDecoration {
        return VerticalSpacingItemDecoration(15)
    }

}