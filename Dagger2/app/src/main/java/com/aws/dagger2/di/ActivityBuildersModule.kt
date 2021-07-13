package com.aws.dagger2.di

import com.aws.dagger2.di.auth.AuthModule
import com.aws.dagger2.di.auth.AuthViewModelModule
import com.aws.dagger2.ui.auth.AuthActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector (
        modules = [
            AuthViewModelModule::class,
            AuthModule::class,
        ]
    )
    abstract fun contributeAuthActivity(): AuthActivity

}