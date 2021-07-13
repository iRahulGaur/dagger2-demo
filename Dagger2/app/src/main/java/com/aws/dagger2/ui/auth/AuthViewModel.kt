package com.aws.dagger2.ui.auth

import android.text.TextUtils
import android.util.Log
import androidx.lifecycle.*
import com.aws.dagger2.models.User
import com.aws.dagger2.network.auth.AuthApi
import com.aws.dagger2.util.UtilsManager
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AuthViewModel @Inject constructor(private val authApi: AuthApi): ViewModel() {

    companion object {
        private const val TAG = "AuthViewModel"
    }

    private val userIDString: MutableLiveData<String?> = MutableLiveData()
    fun getUserID(): MutableLiveData<String?> {
        return userIDString
    }

    private var authUser: MediatorLiveData<AuthResource<out User?>> = MediatorLiveData()
    fun observeUser(): LiveData<AuthResource<out User?>> {
        return authUser
    }

    private fun authenticateWithId(userID: Int) {
        authUser.value = AuthResource.loading(null)

        val source = LiveDataReactiveStreams.fromPublisher(
            authApi.getUser(userID)
                .onErrorReturn {
                    return@onErrorReturn User("",-1,"","","","")
                }
                .map {
                    if (it.id == -1)
                        return@map AuthResource.error("Could not authenticate", null)
                    else
                        return@map AuthResource.authenticated(it)
                }
                .subscribeOn(Schedulers.io())
        )

        authUser.addSource(source) {
            authUser.value = it
            authUser.removeSource(source)
        }
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
                authUser.value = AuthResource.error("Please enter your id", null)
            }
        } else {
            authUser.value = AuthResource.error("Please enter your id", null)
        }
    }
}