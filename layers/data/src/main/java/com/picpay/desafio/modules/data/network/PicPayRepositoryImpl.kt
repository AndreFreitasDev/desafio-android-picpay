package com.picpay.desafio.modules.data.network

import com.picpay.desafio.modules.data.network.schema.UserResponse

class PicPayRepositoryImpl(
    private val service: PicPayService
) : PicPayRepository {

    override suspend fun getUsers(): List<UserResponse> {
        return service.getUsers()
    }
}