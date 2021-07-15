package com.aws.dagger2.network.main

import com.aws.dagger2.models.PostModel
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MainApi {

    @GET("posts")
    fun getUserPost(
        @Query("userId") id: Int
    ): Flowable<List<PostModel>>

}