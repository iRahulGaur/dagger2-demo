package com.aws.dagger2.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PostModel(
    @SerializedName("body")
    @Expose
    val body: String, // rerum ut et numquam laborum officiis sequi cumque quod
    @SerializedName("id")
    @Expose
    val id: Int, // 33
    @SerializedName("title")
    @Expose
    val title: String, // qui explicabo molestiae dolorem
    @SerializedName("userId")
    @Expose
    val userId: Int // 4
)