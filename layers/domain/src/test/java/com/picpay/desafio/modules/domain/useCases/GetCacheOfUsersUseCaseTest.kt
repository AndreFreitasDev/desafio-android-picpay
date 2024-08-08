package com.picpay.desafio.modules.domain.useCases

import com.google.common.truth.Truth
import com.picpay.desafio.modules.data.database.dao.UserDao
import com.picpay.desafio.modules.data.database.model.UserDb
import com.picpay.desafio.modules.tests.ExecutorExtensionPerMethod
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
import org.junit.jupiter.api.extension.RegisterExtension

class GetCacheOfUsersUseCaseTest {

    @JvmField
    @RegisterExtension
    val executorExtension = ExecutorExtensionPerMethod()

    @RelaxedMockK
    private lateinit var userDao: UserDao

    private lateinit var useCase: GetCacheOfUsersUseCase

    @BeforeEach
    fun beforeTests() {
        MockKAnnotations.init(this)
        useCase = GetCacheOfUsersUseCase(userDao, executorExtension.testDispatcherProvider())
    }

    @AfterEach
    fun afterTests() {
        unmockkAll()
    }

    @Test
    @DisplayName("Should return users from userDao")
    fun `scenario 1`() = runTest {
        val usersDb = listOf(
            UserDb(
                id = 1,
                img = "img",
                name = "name",
                username = "username"
            )
        )

        coEvery {
            userDao.getAll()
        } coAnswers {
            usersDb
        }

        val users = useCase.execute()

        Truth.assertThat(users.size).isEqualTo(1)
        coVerify(exactly = 1) { userDao.getAll() }
    }
}