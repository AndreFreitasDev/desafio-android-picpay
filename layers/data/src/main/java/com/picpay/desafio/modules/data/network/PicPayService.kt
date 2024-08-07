package com.picpay.desafio.modules.data.network

import com.picpay.desafio.modules.data.network.schema.UserResponse
import retrofit2.http.GET

interface PicPayService {

    @GET("users")
    suspend fun getUsers(): List<UserResponse>
}