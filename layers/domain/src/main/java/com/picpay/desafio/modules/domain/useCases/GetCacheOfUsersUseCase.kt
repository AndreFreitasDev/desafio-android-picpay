package com.picpay.desafio.modules.domain.useCases

import com.picpay.desafio.modules.data.database.dao.UserDao
import com.picpay.desafio.modules.commons.coroutines.DispatcherProvider
import com.picpay.desafio.modules.domain.model.User
import kotlinx.coroutines.withContext

class GetCacheOfUsersUseCase(
    private val userDao: UserDao,
    private val dispatcher: DispatcherProvider
) {

    suspend fun execute(): List<User> = withContext(dispatcher.io) {
        val users = userDao.getAll().mapNotNull { item ->
            val id = item.id?.toInt() ?: return@mapNotNull null
            User(
                id = id,
                img = item.img,
                name = item.name,
                username = item.username
            )
        }
        return@withContext users
    }
}