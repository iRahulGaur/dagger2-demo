package com.aws.dagger2.di

import com.aws.dagger2.di.auth.AuthModule
import com.aws.dagger2.di.auth.AuthViewModelModule
import com.aws.dagger2.di.main.MainFragmentBuildersModule
import com.aws.dagger2.di.main.MainModule
import com.aws.dagger2.di.main.MainViewModelModule
import com.aws.dagger2.ui.auth.AuthActivity
import com.aws.dagger2.ui.main.MainActivity
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

    @ContributesAndroidInjector (
        modules = [
            MainFragmentBuildersModule::class,
            MainViewModelModule::class,
            MainModule::class,
        ]
    )
    abstract fun contributeMainActivity(): MainActivity

}