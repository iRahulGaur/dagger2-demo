package com.aws.dagger2.ui.auth

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.aws.dagger2.R
import com.aws.dagger2.databinding.ActivityAuthBinding
import com.aws.dagger2.util.UtilsManager
import com.aws.dagger2.viewModels.ViewModelProvidersFactory
import com.bumptech.glide.RequestManager
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class AuthActivity : DaggerAppCompatActivity() {

    companion object {
        private const val TAG = "AuthActivity"
    }

    private lateinit var binding: ActivityAuthBinding
    private lateinit var viewModel: AuthViewModel

    @Inject
    lateinit var providersFactory: ViewModelProvidersFactory

    @Inject
    lateinit var logo: Drawable

    @Inject
    lateinit var requestManager: RequestManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_auth)
        viewModel = ViewModelProvider(this, providersFactory).get(AuthViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        viewModel.init()

        setLogo()
        subscribeObservers()
    }

    private fun subscribeObservers() {
        viewModel.observeUser().observe(this, { user ->
            if (user != null) {
                UtilsManager.log(TAG, "subscribeObservers: we have user email = ${user.email}")
                Toast.makeText(this, "User logged in = ${user.name}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setLogo() {
        requestManager
            .load(logo)
            .into(findViewById(R.id.login_logo))
    }

}