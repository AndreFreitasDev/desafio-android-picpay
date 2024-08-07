package com.picpay.desafio.modules.data.database.dao

import com.picpay.desafio.modules.data.database.model.UserDb

interface UserDao {

    suspend fun insertAll(user: List<UserDb>)

    suspend fun deleteAll()

    suspend fun update(user: UserDb): Int

    suspend fun getAll(): List<UserDb>
}