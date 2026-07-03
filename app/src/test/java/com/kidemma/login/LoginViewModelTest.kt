package com.kidemma.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.kidemma.authentication.login.presentation.LoginContentProvider
import com.kidemma.authentication.login.presentation.LoginContract
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest

/*
 * File: LoginViewModelTest
 * Description: [Short description]
 *
 * Created by: Jorge Luis Hernández Núñez
 * Created on: 24/02/26
 * Last modified: 24/02/26
 */
class LoginViewModelTest : KoinTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private val testDispatcher = StandardTestDispatcher()


    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun `uiData is initialized correctly from content provider`() = runTest {
        // GIVEN
        val testSubject = LoginViewModelTestFactory.givenALoginViewModel()
        val viewModel = testSubject.viewModel

        // WHEN
        val actualUiData = viewModel.uiData
        val expectedUiData = LoginContentProvider.getLoginScreenData()

        // THEN
        assertThat(actualUiData).isEqualTo(expectedUiData)
    }

    @Test
    fun `given error state, when OnEmailChange, then email updates and error is cleared`() =
        runTest {
            val testSubject = LoginViewModelTestFactory.givenALoginViewModel()
            val viewModel = testSubject.viewModel

            // GIVEN: Set an error state (e.g. invalid email)
            viewModel.processIntent(LoginContract.Intent.OnEmailChange("invalid-email"))
            assertThat(viewModel.state.value.email.error).isNotNull()

            // WHEN
            viewModel.processIntent(LoginContract.Intent.OnEmailChange("test@example.com"))

            // THEN
            assertThat(viewModel.state.value.email.value).isEqualTo("test@example.com")
            assertThat(viewModel.state.value.email.error).isNull()
            assertThat(viewModel.state.value.errorMessage).isNull()
        }

    @Test
    fun `given error state, when OnPasswordChange, then password updates and error is cleared`() =
        runTest {
            val testSubject = LoginViewModelTestFactory.givenALoginViewModel()
            val viewModel = testSubject.viewModel

            // GIVEN: Set an error state (e.g. too short)
            viewModel.processIntent(LoginContract.Intent.OnPasswordChange("123"))
            assertThat(viewModel.state.value.password.error).isNotNull()

            val newPassword = "newPassword"
            // WHEN
            viewModel.processIntent(LoginContract.Intent.OnPasswordChange(newPassword))

            // THEN
            assertThat(viewModel.state.value.password.value).isEqualTo(newPassword)
            assertThat(viewModel.state.value.password.error).isNull()
            assertThat(viewModel.state.value.errorMessage).isNull()
        }

    @Test
    fun `when OnTogglePasswordVisibility, then isPasswordVisible state is toggled`() =
        runTest {
            val testSubject = LoginViewModelTestFactory.givenALoginViewModel()
            val viewModel = testSubject.viewModel
            assertThat(viewModel.state.value.isPasswordVisible).isFalse()

            // WHEN
            viewModel.processIntent(LoginContract.Intent.OnTogglePasswordVisibility)

            // THEN
            assertThat(viewModel.state.value.isPasswordVisible).isTrue()

            // WHEN
            viewModel.processIntent(LoginContract.Intent.OnTogglePasswordVisibility)

            // THEN
            assertThat(viewModel.state.value.isPasswordVisible).isFalse()
        }

    @Test
    fun `given error state, when OnErrorShown, then errorMessage is cleared`() =
        runTest {
            val testSubject = LoginViewModelTestFactory.givenALoginViewModel()
            val viewModel = testSubject.viewModel

            //WHEN
            viewModel.processIntent(LoginContract.Intent.OnLoginClicked)
            assertThat(viewModel.state.value).isNotNull()

            viewModel.processIntent(LoginContract.Intent.OnErrorShown)

            //THEN
            assertThat(viewModel.state.value.errorMessage).isNull()

        }

    @Test
    fun `given invalid email format, when OnEmailChange, then email error is set`() =
        runTest {
            val testSubject = LoginViewModelTestFactory.givenALoginViewModel()
            val viewModel = testSubject.viewModel

            // WHEN
            viewModel.processIntent(LoginContract.Intent.OnEmailChange("user_without_at_symbol.com"))

            // THEN
            assertThat(viewModel.state.value.email.error).isNotNull()
        }

    @Test
    fun `given blank required fields, when OnLoginClicked, then submit should not proceed`() =
        runTest {
            val testSubject = LoginViewModelTestFactory.givenALoginViewModel()
            val viewModel = testSubject.viewModel
            // Both fields are required and blank initially
            assertThat(viewModel.state.value.isSubmitEnabled).isFalse()

            // WHEN
            viewModel.processIntent(LoginContract.Intent.OnLoginClicked)

            // THEN
            assertThat(viewModel.state.value.isLoading).isFalse()
        }

    @Test
    fun `given valid credentials, when OnLoginClicked, then isLoading becomes true`() = runTest {
        // GIVEN
        val testSubject = LoginViewModelTestFactory.givenALoginViewModel()
        val viewModel = testSubject.viewModel

        viewModel.processIntent(LoginContract.Intent.OnEmailChange("valid@email.com"))
        viewModel.processIntent(LoginContract.Intent.OnPasswordChange("12345678"))
        assertThat(viewModel.state.value.isSubmitEnabled).isTrue()

        // WHEN
        viewModel.processIntent(LoginContract.Intent.OnLoginClicked)
        testDispatcher.scheduler.runCurrent()

        // THEN
        assertThat(viewModel.state.value.isLoading).isTrue()
        assertThat(viewModel.state.value.errorMessage).isNull()
        assertThat(viewModel.state.value.email.error).isNull()
        assertThat(viewModel.state.value.password.error).isNull()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `given admin credentials, when OnLoginClicked, then NavigateToAdminHome effect is emitted`() = runTest {
        // GIVEN
        val testSubject = LoginViewModelTestFactory.givenALoginViewModel()
        val viewModel = testSubject.viewModel
        val effects = mutableListOf<LoginContract.Effect>()
        val job = launch {
            viewModel.effects.collect { effects.add(it) }
        }

        viewModel.processIntent(LoginContract.Intent.OnEmailChange("admin@test.com"))
        viewModel.processIntent(LoginContract.Intent.OnPasswordChange("12345678"))

        // WHEN
        viewModel.processIntent(LoginContract.Intent.OnLoginClicked)
        testDispatcher.scheduler.advanceTimeBy(1501)
        testDispatcher.scheduler.runCurrent()

        // THEN
        assertThat(effects).contains(LoginContract.Effect.NavigateToAdminHome)
        job.cancel()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `given user home credentials, when OnLoginClicked, then NavigateToUserHome effect is emitted`() = runTest {
        // GIVEN
        val testSubject = LoginViewModelTestFactory.givenALoginViewModel()
        val viewModel = testSubject.viewModel
        val effects = mutableListOf<LoginContract.Effect>()
        val job = launch {
            viewModel.effects.collect { effects.add(it) }
        }

        viewModel.processIntent(LoginContract.Intent.OnEmailChange("home@test.com"))
        viewModel.processIntent(LoginContract.Intent.OnPasswordChange("12345678"))

        // WHEN
        viewModel.processIntent(LoginContract.Intent.OnLoginClicked)
        testDispatcher.scheduler.advanceTimeBy(1501)
        testDispatcher.scheduler.runCurrent()

        // THEN
        assertThat(effects).contains(LoginContract.Effect.NavigateToUserHome)
        job.cancel()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `given error credentials, when OnLoginClicked, then error message is set and isLoading is false`() = runTest {
        // GIVEN
        val testSubject = LoginViewModelTestFactory.givenALoginViewModel()
        val viewModel = testSubject.viewModel

        viewModel.processIntent(LoginContract.Intent.OnEmailChange("error@test.com"))
        viewModel.processIntent(LoginContract.Intent.OnPasswordChange("12345678"))

        // WHEN
        viewModel.processIntent(LoginContract.Intent.OnLoginClicked)
        testDispatcher.scheduler.advanceTimeBy(1501)
        testDispatcher.scheduler.runCurrent()

        // THEN
        assertThat(viewModel.state.value.isLoading).isFalse()
        assertThat(viewModel.state.value.errorMessage).isEqualTo(viewModel.uiData.errorInvalidCredentials)
    }
}
