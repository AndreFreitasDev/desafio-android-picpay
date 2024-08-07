package com.picpay.desafio.modules.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.picpay.desafio.modules.data.database.dao.RoomUserDao
import com.picpay.desafio.modules.data.database.entities.UserEntity

@Database(version = 1, exportSchema = false, entities = [
    UserEntity::class
])
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): RoomUserDao
}