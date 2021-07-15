package com.aws.dagger2.ui.auth

import android.text.TextUtils
import android.util.Log
import androidx.lifecycle.*
import com.aws.dagger2.SessionManager
import com.aws.dagger2.models.UserModel
import com.aws.dagger2.network.auth.AuthApi
import com.aws.dagger2.util.UtilsManager
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AuthViewModel @Inject constructor(private val authApi: AuthApi, private val sessionManager: SessionManager): ViewModel() {

    companion object {
        private const val TAG = "AuthViewModel"
    }

    private val userIDString: MutableLiveData<String?> = MutableLiveData()
    fun getUserID(): MutableLiveData<String?> {
        return userIDString
    }

    fun observeAuthState(): LiveData<AuthResource<out UserModel?>> {
        return sessionManager.getAuthUser()
    }

    private fun authenticateWithId(userID: Int) {
        UtilsManager.log(TAG, "authenticateWithId: attempting to login")
        sessionManager.authenticateWithId(queryUserId(userID))
    }

    private fun queryUserId(id: Int): LiveData<AuthResource<out UserModel?>> {
        return LiveDataReactiveStreams.fromPublisher(
            authApi.getUser(id)
                .onErrorReturn {
                    return@onErrorReturn UserModel("",-1,"","","","")
                }
                .map {
                    if (it.id == -1)
                        return@map AuthResource.error("Could not authenticate", null)
                    else
                        return@map AuthResource.authenticated(it)
                }
                .subscribeOn(Schedulers.io())
        )
    }

    fun init() {
        Log.e(TAG, "init called")
    }

    fun attemptLogin() {
        if (userIDString.value != null) {
            if (!TextUtils.isEmpty(userIDString.value)) {
                UtilsManager.log(TAG, "attemptLogin: userID is valid $")
                authenticateWithId(userIDString.value!!.toInt())
            } else {
                sessionManager.setError("Please enter your id")
            }
        } else {
            sessionManager.setError("Please enter your id")
        }
    }
}