package com.picpay.desafio.modules.domain.useCases

import com.picpay.desafio.modules.data.network.PicPayRepository
import com.picpay.desafio.modules.commons.coroutines.DispatcherProvider
import com.picpay.desafio.modules.domain.model.User
import kotlinx.coroutines.withContext

class GetUsersUseCase(
    private val saveCacheOfUsersUseCase: SaveCacheOfUsersUseCase,
    private val getCacheOfUsersUseCase: GetCacheOfUsersUseCase,
    private val repository: PicPayRepository,
    private val dispatcher: DispatcherProvider
) {

    suspend fun execute(): List<User> = withContext(dispatcher.io) {
        var users = getUsersFromApi()?.let { users ->
            saveCacheOfUsersUseCase.execute(users)
            users
        }
        if (users == null) {
            users = getUsersCached()
        }
        return@withContext users
    }

    private suspend fun getUsersCached(): List<User> {
        return kotlin.runCatching {
            getCacheOfUsersUseCase.execute()
        }.getOrNull().orEmpty()
    }

    private suspend fun getUsersFromApi(): List<User>? {
        try {
            val users = repository.getUsers().mapNotNull { item ->
                val id = item.id ?: return@mapNotNull null
                val img = item.img ?: return@mapNotNull null
                val name = item.name ?: return@mapNotNull null
                val username = item.username ?: return@mapNotNull null
                User(
                    id = id,
                    img = img,
                    name = name,
                    username = username
                )
            }
            return users
        } catch (e: Throwable) {
            return null
        }
    }
}