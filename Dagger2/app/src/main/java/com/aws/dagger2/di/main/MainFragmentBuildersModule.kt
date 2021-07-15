package com.aws.dagger2.di.main

import com.aws.dagger2.ui.main.post.PostFragment
import com.aws.dagger2.ui.main.profile.ProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeProfileFragment(): ProfileFragment

    @ContributesAndroidInjector
    abstract fun contributePostFragment(): PostFragment

}