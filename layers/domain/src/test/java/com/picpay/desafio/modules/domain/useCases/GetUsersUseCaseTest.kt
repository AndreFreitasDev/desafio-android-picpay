package com.picpay.desafio.modules.domain.useCases

import com.google.common.truth.Truth
import com.picpay.desafio.modules.data.network.PicPayRepository
import com.picpay.desafio.modules.data.network.schema.UserResponse
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

class GetUsersUseCaseTest {

    @JvmField
    @RegisterExtension
    val executorExtension = ExecutorExtensionPerMethod()

    @RelaxedMockK
    private lateinit var saveCacheOfUsersUseCase: SaveCacheOfUsersUseCase
    @RelaxedMockK
    private lateinit var getCacheOfUsersUseCase: GetCacheOfUsersUseCase
    @RelaxedMockK
    private lateinit var repository: PicPayRepository

    private lateinit var useCase: GetUsersUseCase

    @BeforeEach
    fun beforeTests() {
        MockKAnnotations.init(this)
        useCase = GetUsersUseCase(
            saveCacheOfUsersUseCase = saveCacheOfUsersUseCase,
            getCacheOfUsersUseCase = getCacheOfUsersUseCase,
            repository = repository,
            dispatcher = executorExtension.testDispatcherProvider()
        )
    }

    @AfterEach
    fun afterTests() {
        unmockkAll()
    }

    @Test
    @DisplayName("Should return users from API")
    fun `scenario 1`() = runTest {
        val usersApi = listOf(
            UserResponse(
                id = 1,
                img = "img",
                name = "User API",
                username = "username"
            )
        )
        coEvery {
            repository.getUsers()
        } coAnswers {
            usersApi
        }

        val usersCached = listOf(
            User(
                id = 2,
                img = "img",
                name = "User Cache",
                username = "username"
            )
        )
        coEvery {
            getCacheOfUsersUseCase.execute()
        } coAnswers {
            usersCached
        }

        val slotSaveCacheUsers = slot<List<User>>()
        coEvery {
            saveCacheOfUsersUseCase.execute(capture(slotSaveCacheUsers))
        } coAnswers {

        }

        val users = useCase.execute()

        Truth.assertThat(users.size).isEqualTo(1)
        Truth.assertThat(users.first().name).isEqualTo("User API")

        coVerify(exactly = 1) { repository.getUsers() }
        coVerify(exactly = 1) { saveCacheOfUsersUseCase.execute(slotSaveCacheUsers.captured) }
        coVerify(exactly = 0) { getCacheOfUsersUseCase.execute() }
    }

    @Test
    @DisplayName("Should return users from Cache")
    fun `scenario 2`() = runTest {
        coEvery {
            repository.getUsers()
        } coAnswers {
            throw RuntimeException("internal error")
        }

        val usersCached = listOf(
            User(
                id = 1,
                img = "img",
                name = "User Cache",
                username = "username"
            )
        )
        coEvery {
            getCacheOfUsersUseCase.execute()
        } coAnswers {
            usersCached
        }

        val slotSaveCacheUsers = slot<List<User>>()
        coEvery {
            saveCacheOfUsersUseCase.execute(capture(slotSaveCacheUsers))
        } coAnswers {

        }

        val users = useCase.execute()

        Truth.assertThat(users.size).isEqualTo(1)
        Truth.assertThat(users.first().name).isEqualTo("User Cache")

        coVerify(exactly = 1) { repository.getUsers() }
        coVerify(exactly = 0) { saveCacheOfUsersUseCase.execute(any()) }
        coVerify(exactly = 1) { getCacheOfUsersUseCase.execute() }
    }
}