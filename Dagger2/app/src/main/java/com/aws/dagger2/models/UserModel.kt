package com.aws.dagger2.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserModel(
    @SerializedName("email")
    @Expose
    val email: String, // Rey.Padberg@karina.biz
    @SerializedName("id")
    @Expose
    val id: Int, // 10
    @SerializedName("name")
    @Expose
    val name: String, // Clementina DuBuque
    @SerializedName("phone")
    @Expose
    val phone: String, // 024-648-3804
    @SerializedName("username")
    @Expose
    val username: String, // Moriah.Stanton
    @SerializedName("website")
    @Expose
    val website: String // ambrose.net
)