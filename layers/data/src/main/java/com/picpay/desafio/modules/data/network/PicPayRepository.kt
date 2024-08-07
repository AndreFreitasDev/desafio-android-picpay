package com.picpay.desafio.modules.data.network

import com.picpay.desafio.modules.data.network.schema.UserResponse

interface PicPayRepository {
    suspend fun getUsers(): List<UserResponse>
}