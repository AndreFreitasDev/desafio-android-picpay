package com.picpay.desafio.modules.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.picpay.desafio.modules.data.database.entities.UserEntity

@Dao
interface RoomUserDao {

    @Insert
    fun insertAll(vararg user: UserEntity)

    @Query("DELETE FROM user")
    fun deleteAll()

    @Update
    fun update(user: UserEntity): Int

    @Query("SELECT * FROM user")
    fun getAll(): List<UserEntity>
}