package com.picpay.desafio.modules.domain.useCases

import com.google.common.truth.Truth
import com.picpay.desafio.modules.data.database.dao.UserDao
import com.picpay.desafio.modules.data.database.model.UserDb
import com.picpay.desafio.modules.domain.model.User
import com.picpay.desafio.modules.tests.ExecutorExtensionPerMethod
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.slot
import io.mockk.unmockkAll
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension

class SaveCacheOfUsersUseCaseTest {

    @JvmField
    @RegisterExtension
    val executorExtension = ExecutorExtensionPerMethod()

    @RelaxedMockK
    private lateinit var userDao: UserDao

    private lateinit var useCase: SaveCacheOfUsersUseCase

    @BeforeEach
    fun beforeTests() {
        MockKAnnotations.init(this)
        useCase = SaveCacheOfUsersUseCase(userDao, executorExtension.testDispatcherProvider())
    }

    @AfterEach
    fun afterTests() {
        unmockkAll()
    }

    @Test
    @DisplayName("Should delete old users and save new users")
    fun `scenario 1`() = runTest {
        val users = listOf(
            User(
                id = 1,
                img = "img",
                name = "name",
                username = "username"
            )
        )

        val slot = slot<List<UserDb>>()
        coEvery {
            userDao.insertAll(capture(slot))
        } coAnswers {
            callOriginal()
        }

        useCase.execute(users)

        Truth.assertThat(slot.captured.size).isEqualTo(1)
        coVerify(exactly = 1) { userDao.deleteAll() }
        coVerify(exactly = 1) { userDao.insertAll(slot.captured) }
    }
}