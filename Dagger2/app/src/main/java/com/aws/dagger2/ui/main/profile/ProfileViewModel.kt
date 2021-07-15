package com.aws.dagger2.ui.main.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.aws.dagger2.SessionManager
import com.aws.dagger2.models.UserModel
import com.aws.dagger2.ui.auth.AuthResource
import javax.inject.Inject

class ProfileViewModel @Inject constructor(private val sessionManager: SessionManager) : ViewModel() {

    fun getAuthUser(): LiveData<AuthResource<out UserModel?>> {
        return sessionManager.getAuthUser()
    }

}