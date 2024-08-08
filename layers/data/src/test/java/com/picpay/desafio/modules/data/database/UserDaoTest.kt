package com.picpay.desafio.modules.data.database

import com.google.common.truth.Truth
import com.picpay.desafio.modules.data.database.dao.RoomUserDao
import com.picpay.desafio.modules.data.database.dao.UserDao
import com.picpay.desafio.modules.data.database.dao.UserDaoImpl
import com.picpay.desafio.modules.data.database.entities.UserEntity
import com.picpay.desafio.modules.data.database.model.UserDb
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.unmockkAll
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class UserDaoTest {

    @RelaxedMockK
    private lateinit var roomDatabase: RoomUserDao

    private lateinit var dao: UserDao

    @BeforeEach
    fun beforeTests() {
        MockKAnnotations.init(this)
        dao = UserDaoImpl(roomDatabase)
    }

    @AfterEach
    fun afterTests() {
        unmockkAll()
    }

    @Test
    @DisplayName("When UserDao is called with deleteAll, Then should called roomDatabase with deleteAll")
    fun `scenario 1`() = runTest {
        dao.deleteAll()

        coVerify (exactly = 1) {
            roomDatabase.deleteAll()
        }
    }

    @Test
    @DisplayName("When UserDao is called with update, Then should called roomDatabase with update")
    fun `scenario 2`() = runTest {
        val user = UserDb(
            id = 1,
            img = "img",
            name = "name",
            username = "username"
        )
        dao.update(user)

        coVerify (exactly = 1) {
            roomDatabase.update(any())
        }
    }

    @Test
    @DisplayName("When UserDao is called with insertAll, Then should called roomDatabase with insertAll")
    fun `scenario 3`() = runTest {
        val users: List<UserDb> = listOf(
            UserDb(
                id = 1,
                img = "img",
                name = "name",
                username = "username"
            )
        )
        dao.insertAll(users)

        coVerify (exactly = 1) {
            roomDatabase.insertAll(any())
        }
    }

    @Test
    @DisplayName("When UserDao is called with getAll, Then should called roomDatabase with getAll")
    fun `scenario 4`() = runTest {
        val usersEntity: List<UserEntity> = listOf(
            UserEntity(
                id = 1,
                img = "img",
                name = "name 1",
                username = "username"
            ),
            UserEntity(
                id = 2,
                img = "img",
                name = "name 2",
                username = "username"
            )
        )
        coEvery {
            roomDatabase.getAll()
        } coAnswers {
            usersEntity
        }

        val users = dao.getAll()
        Truth.assertThat(users.size).isEqualTo(2)

        coVerify (exactly = 1) {
            roomDatabase.getAll()
        }
    }
}