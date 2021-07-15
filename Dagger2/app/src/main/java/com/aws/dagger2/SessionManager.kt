package com.aws.dagger2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.aws.dagger2.models.UserModel
import com.aws.dagger2.ui.auth.AuthResource
import com.aws.dagger2.util.UtilsManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor() {

    companion object {
        private const val TAG = "SessionManager"
    }

    private val cachedUserModel: MediatorLiveData<AuthResource<out UserModel?>> = MediatorLiveData()

    fun authenticateWithId(source: LiveData<AuthResource<out UserModel?>>) {
        if (cachedUserModel.value != null) {
            cachedUserModel.value = AuthResource.loading(null)
            cachedUserModel.addSource(source
            ) {
                cachedUserModel.value = it
                cachedUserModel.removeSource(source)
            }
        }
    }

    fun setError(message: String) {
        cachedUserModel.value = AuthResource.error(message+"", null)
    }

    fun logout() {
        UtilsManager.log(TAG, "logout: logging out user")
        cachedUserModel.value = AuthResource.logout()
    }

    fun getAuthUser(): LiveData<AuthResource<out UserModel?>> {
        return cachedUserModel
    }

}