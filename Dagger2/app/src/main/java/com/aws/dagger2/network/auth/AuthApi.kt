package com.aws.dagger2.network.auth

import com.aws.dagger2.models.UserModel
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

interface AuthApi {

    @GET("users/{id}")
    fun getUser(
        @Path("id") id: Int
    ): Flowable<UserModel>

}