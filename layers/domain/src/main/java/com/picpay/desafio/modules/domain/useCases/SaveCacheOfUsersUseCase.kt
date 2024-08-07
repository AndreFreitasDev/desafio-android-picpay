package com.picpay.desafio.modules.domain.useCases

import com.picpay.desafio.modules.data.database.dao.UserDao
import com.picpay.desafio.modules.data.database.model.UserDb
import com.picpay.desafio.modules.domain.coroutines.DispatcherProvider
import com.picpay.desafio.modules.domain.model.User
import kotlinx.coroutines.withContext

class SaveCacheOfUsersUseCase(
    private val userDao: UserDao,
    private val dispatcher: DispatcherProvider
) {

    suspend fun execute(users: List<User>) = withContext(dispatcher.io()) {
        val usersDb = users.map { user ->
            UserDb(
                id = null,
                img = user.img,
                name = user.name,
                username = user.username
            )
        }
        userDao.deleteAll()
        userDao.insertAll(usersDb)
    }
}