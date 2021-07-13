package com.aws.dagger2

import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import com.aws.dagger2.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class MyApplication : DaggerApplication(), ViewModelStoreOwner {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }

    private val appViewModelStore: ViewModelStore by lazy {
        ViewModelStore()
    }
    override fun getViewModelStore(): ViewModelStore {
        return appViewModelStore
    }

}
