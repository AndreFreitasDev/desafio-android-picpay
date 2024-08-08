package com.picpay.desafio.modules.presentation.ui.main

import com.google.common.truth.Truth.assertThat
import com.picpay.desafio.modules.commons.resources.StringValue
import com.picpay.desafio.modules.domain.model.User
import com.picpay.desafio.modules.domain.useCases.GetUsersUseCase
import com.picpay.desafio.modules.presentation.ExecutorExtensionPerMethod
import com.picpay.desafio.modules.presentation.R
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.unmockkAll
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelTest {

    @JvmField
    @RegisterExtension
    val executorExtension = ExecutorExtensionPerMethod()

    @RelaxedMockK
    private lateinit var getUsersUseCase: GetUsersUseCase

    private lateinit var viewModel: MainViewModel

    @BeforeEach
    fun beforeTests() {
        MockKAnnotations.init(this)
        viewModel = MainViewModel(getUsersUseCase, executorExtension.testDispatcherProvider())
    }

    @AfterEach
    fun afterTests() {
        unmockkAll()
    }

    @Test
    @DisplayName("When intent is LoadUsers, Then should emit UsersLoaded state")
    fun `scenario 1`() = runTest {
        val users = listOf(
            User(
                id = 1,
                img = "img",
                name = "User 1",
                username = "username"
            ),
            User(
                id = 2,
                img = "img",
                name = "User 2",
                username = "username"
            )
        )
        coEvery {
            getUsersUseCase.execute()
        } coAnswers {
            users
        }

        val values = mutableListOf<MainViewState>()
        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.state.toList(values)
        }

        viewModel.handleIntent(MainIntent.LoadUsers)
        advanceUntilIdle()

        assertThat(values[1]).isInstanceOf(MainViewState.ShowLoading::class.java)
        assertThat(values[2]).isInstanceOf(MainViewState.HideLoading::class.java)
        values[3].apply {
            assertThat(this).isInstanceOf(MainViewState.UsersLoaded::class.java)
            val usersLoaded = this as MainViewState.UsersLoaded
            assertThat(usersLoaded.users.size).isEqualTo(2)
        }
    }

    @Test
    @DisplayName("When intent is LoadUsers and occurs error in getUsersUseCase, Then should emit PresentError state")
    fun `scenario 2`() = runTest {
        coEvery {
            getUsersUseCase.execute()
        } coAnswers {
            throw RuntimeException("Error")
        }

        val values = mutableListOf<MainViewState>()
        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.state.toList(values)
        }

        viewModel.handleIntent(MainIntent.LoadUsers)
        advanceUntilIdle()

        assertThat(values[1]).isInstanceOf(MainViewState.ShowLoading::class.java)
        assertThat(values[2]).isInstanceOf(MainViewState.HideLoading::class.java)
        values[3].apply {
            assertThat(this).isInstanceOf(MainViewState.PresentError::class.java)
            val message = (this as MainViewState.PresentError).message
            val stringResource = message as StringValue.StringResource
            assertThat(stringResource.resId).isEqualTo(R.string.error)
        }
    }
}