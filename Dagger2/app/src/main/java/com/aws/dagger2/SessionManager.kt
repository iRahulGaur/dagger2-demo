package com.aws.dagger2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.aws.dagger2.models.User
import com.aws.dagger2.ui.auth.AuthResource
import com.aws.dagger2.util.UtilsManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor() {

    companion object {
        private const val TAG = "SessionManager"
    }

    private val cachedUser: MediatorLiveData<AuthResource<out User?>> = MediatorLiveData()

    fun authenticateWithId(source: LiveData<AuthResource<out User?>>) {
        if (cachedUser.value != null) {
            cachedUser.value = AuthResource.loading(null)
            cachedUser.addSource(source
            ) {
                cachedUser.value = it
                cachedUser.removeSource(source)
            }
        }
    }

    fun setError(message: String) {
        cachedUser.value = AuthResource.error(message+"", null)
    }

    fun logout() {
        UtilsManager.log(TAG, "logout: logging out user")
        cachedUser.value = AuthResource.logout()
    }

    fun getAuthUser(): LiveData<AuthResource<out User?>> {
        return cachedUser
    }

}