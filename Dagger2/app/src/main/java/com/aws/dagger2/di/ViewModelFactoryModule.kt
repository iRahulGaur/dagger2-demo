package com.aws.dagger2.di

import androidx.lifecycle.ViewModelProvider
import com.aws.dagger2.viewModels.ViewModelProvidersFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(modelProviderFactory: ViewModelProvidersFactory): ViewModelProvider.Factory

}