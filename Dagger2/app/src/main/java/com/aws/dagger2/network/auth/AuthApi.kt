package com.aws.dagger2.network.auth

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

interface AuthApi {

    @GET
    fun getStaff(): Call<ResponseBody>

}