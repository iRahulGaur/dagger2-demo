package com.aws.dagger2

import android.content.Intent
import android.os.Bundle
import com.aws.dagger2.ui.auth.AuthActivity
import com.aws.dagger2.ui.auth.AuthResource
import com.aws.dagger2.util.UtilsManager
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity: DaggerAppCompatActivity() {

    companion object {
        private const val TAG = "BaseActivity"
    }

    @Inject
    lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribeObservers()
    }

    private fun subscribeObservers() {
        sessionManager.getAuthUser().observe(this ) { authResource ->
            if (authResource != null) {
                when (authResource.status) {
                    AuthResource.AuthStatus.LOADING -> {
                    }
                    AuthResource.AuthStatus.AUTHENTICATED -> {
                        UtilsManager.log(TAG, "subscribeObservers: welcome ${authResource.data?.name}")
                    }
                    AuthResource.AuthStatus.ERROR -> {
                        UtilsManager.log(TAG, "subscribeObservers: error ${authResource.message}")
                    }
                    AuthResource.AuthStatus.NOT_AUTHENTICATED -> {
                        UtilsManager.log(TAG, "subscribeObservers: not authenticate")
                        navLoginScreen()
                    }
                }
            }
        }
    }

    private fun navLoginScreen() {
        val logoutIntent = Intent(this, AuthActivity::class.java)
        startActivity(logoutIntent)
        finish()
    }

}