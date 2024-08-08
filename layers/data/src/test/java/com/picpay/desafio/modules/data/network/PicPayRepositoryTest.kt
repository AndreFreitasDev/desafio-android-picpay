package com.picpay.desafio.modules.data.network

import com.google.common.truth.Truth
import com.picpay.desafio.modules.data.network.schema.UserResponse
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

class PicPayRepositoryTest {

    @RelaxedMockK
    private lateinit var service: PicPayService

    private lateinit var repository: PicPayRepository

    @BeforeEach
    fun beforeTests() {
        MockKAnnotations.init(this)
        repository = PicPayRepositoryImpl(service)
    }

    @AfterEach
    fun afterTests() {
        unmockkAll()
    }

    @Test
    @DisplayName("When PicPayRepository is called with getUsers, Then should called PicPayService with getUsers and return users")
    fun `scenario 1`() = runTest {
        val usersResponse: List<UserResponse> = listOf(
            UserResponse(
                id = 1,
                img = "img",
                name = "name 1",
                username = "username"
            ),
            UserResponse(
                id = 2,
                img = "img",
                name = "name 2",
                username = "username"
            )
        )

        coEvery {
            service.getUsers()
        } coAnswers {
            usersResponse
        }

       val users = repository.getUsers()
        Truth.assertThat(users.size).isEqualTo(2)

        coVerify (exactly = 1) {
            service.getUsers()
        }
    }
}