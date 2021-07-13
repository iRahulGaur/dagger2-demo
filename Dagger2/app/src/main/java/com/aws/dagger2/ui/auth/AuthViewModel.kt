package com.aws.dagger2.ui.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class AuthViewModel @Inject constructor(): ViewModel() {

    companion object {
        private const val TAG = "AuthViewModel"
    }

    fun init() {
        Log.e(TAG, "init called")
    }

}