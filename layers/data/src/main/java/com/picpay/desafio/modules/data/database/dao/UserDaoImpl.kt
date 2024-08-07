package com.picpay.desafio.modules.data.database.dao

import com.picpay.desafio.modules.data.database.entities.UserEntity
import com.picpay.desafio.modules.data.database.model.UserDb

class UserDaoImpl(
    private val roomDatabase: RoomUserDao
) : UserDao {

    private fun UserDb.mapToEntity() = UserEntity(
        id = this.id,
        img = this.img,
        name = this.name,
        username = this.username
    )

    override suspend fun deleteAll() {
        roomDatabase.deleteAll()
    }

    override suspend fun insertAll(user: List<UserDb>) {
        val users = user.map { item ->
            item.mapToEntity()
        }
        roomDatabase.insertAll(*users.toTypedArray())
    }

    override suspend fun update(user: UserDb): Int {
        return roomDatabase.update(user.mapToEntity())
    }

    override suspend fun getAll(): List<UserDb> {
        val users = roomDatabase.getAll()
        val usersDb = users.map { item ->
            UserDb(
                id = item.id,
                img = item.img,
                name = item.name,
                username = item.username
            )
        }
        return usersDb
    }
}