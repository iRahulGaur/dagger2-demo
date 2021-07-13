package com.aws.dagger2

import android.graphics.drawable.Drawable
import android.os.Bundle
import com.bumptech.glide.RequestManager
import dagger.android.DaggerActivity
import javax.inject.Inject

class AuthActivity : DaggerActivity() {

    companion object {
        private const val TAG = "AuthActivity"
    }

    @Inject
    lateinit var logo: Drawable
    @Inject
    lateinit var requestManager: RequestManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        setLogo()
    }

    private fun setLogo() {
        requestManager
            .load(logo)
            .into(findViewById(R.id.login_logo))
    }

}