package com.aws.dagger2.di.auth

import androidx.lifecycle.ViewModel
import com.aws.dagger2.di.ViewModelKey
import com.aws.dagger2.ui.auth.AuthViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AuthViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    abstract fun bindViewModelFactory(viewModel: AuthViewModel): ViewModel

}