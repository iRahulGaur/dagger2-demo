package com.aws.dagger2.di.main

import androidx.lifecycle.ViewModel
import com.aws.dagger2.di.ViewModelKey
import com.aws.dagger2.ui.main.post.PostsViewModel
import com.aws.dagger2.ui.main.profile.ProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun bindProfileViewModel(viewModel: ProfileViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PostsViewModel::class)
    abstract fun bindPostsViewModel(viewModel: PostsViewModel): ViewModel

}