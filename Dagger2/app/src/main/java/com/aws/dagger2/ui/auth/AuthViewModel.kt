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

    private val userIDString: MutableLiveData<String> = MutableLiveData()
    fun getUserID(): MutableLiveData<String> {
        return userIDString
    }

    private var authUser: MediatorLiveData<User> = MediatorLiveData()
    fun observeUser(): LiveData<User> {
        return authUser
    }

    private fun authenticateWithId(userID: Int) {
        val source = LiveDataReactiveStreams.fromPublisher(
            authApi.getUser(userID)
                .subscribeOn(Schedulers.io())
        )

        authUser.addSource(source) { user ->
            authUser.value = user
            authUser.removeSource(source)
        }
    }

    fun init() {
        Log.e(TAG, "init called")
    }

    fun attemptLogin() {
        if (TextUtils.isEmpty(userIDString.value.toString())) {
            return
        }
        UtilsManager.log(TAG, "attemptLogin: userID is valid")
        authenticateWithId(userIDString.value.toString().toInt())
    }
}