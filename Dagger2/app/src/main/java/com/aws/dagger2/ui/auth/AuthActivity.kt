package com.aws.dagger2.ui.auth

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import com.aws.dagger2.R
import com.aws.dagger2.viewModels.ViewModelProvidersFactory
import com.bumptech.glide.RequestManager
import dagger.android.DaggerActivity
import javax.inject.Inject

class AuthActivity : DaggerActivity(), ViewModelStoreOwner {

    companion object {
        private const val TAG = "AuthActivity"
    }

    private lateinit var viewModel: AuthViewModel
    
    @Inject
    lateinit var providersFactory: ViewModelProvidersFactory

    @Inject
    lateinit var logo: Drawable

    @Inject
    lateinit var requestManager: RequestManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        viewModel = ViewModelProvider(this, providersFactory).get(AuthViewModel::class.java)
        viewModel.init()
        
        setLogo()
    }

    private fun setLogo() {
        requestManager
            .load(logo)
            .into(findViewById(R.id.login_logo))
    }

    private val appViewModelStore: ViewModelStore by lazy {
        ViewModelStore()
    }

    override fun getViewModelStore(): ViewModelStore {
        return appViewModelStore
    }

}