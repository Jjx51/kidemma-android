package com.kidemma.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.kidemma.authentication.login.presentation.LoginContentProvider
import com.kidemma.authentication.login.presentation.LoginContract
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
    fun `given error state, when OnEmailChange, then email updates and errors are cleared`() =
        runTest {
            val testSubject = LoginViewModelTestFactory.givenALoginViewModel()
            val viewModel = testSubject.viewModel

            //WHEN
            viewModel.processIntent(LoginContract.Intent.OnLoginClicked)
            assertThat(viewModel.state.value).isNotNull()

            viewModel.processIntent(LoginContract.Intent.OnEmailChange("test"))

            //THEN
            assertThat(viewModel.state.value.email).isEqualTo("test")
            assertThat(viewModel.state.value.emailFormatError).isNull()
            assertThat(viewModel.state.value.errorMessage).isNull()

        }

    @Test
    fun `given error state, when OnPasswordChange, then password updates and errors are cleared`() =
        runTest {
            val testSubject = LoginViewModelTestFactory.givenALoginViewModel()
            val viewModel = testSubject.viewModel

            //WHEN
            viewModel.processIntent(LoginContract.Intent.OnLoginClicked)
            assertThat(viewModel.state.value).isNotNull()

            val newPassword = "newPassword"
            viewModel.processIntent(LoginContract.Intent.OnPasswordChange(newPassword))

            //THEN
            assertThat(viewModel.state.value.password).isEqualTo(newPassword)
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
    fun `given invalid email format, when OnLoginClicked, then email format error is set`() =
        runTest {
            val testSubject = LoginViewModelTestFactory.givenALoginViewModel()
            val viewModel = testSubject.viewModel
            viewModel.processIntent(LoginContract.Intent.OnEmailChange("user_without_at_symbol.com"))

            //WHEN
            viewModel.processIntent(LoginContract.Intent.OnLoginClicked)

            //THEN
            assertThat(viewModel.state.value.emailFormatError).isNotNull()

        }

    @Test
    fun `given blank email, when OnLoginClicked, then email errorMessage is set`() =
        runTest {
            val testSubject = LoginViewModelTestFactory.givenALoginViewModel()
            val viewModel = testSubject.viewModel
            viewModel.processIntent(LoginContract.Intent.OnEmailChange("correo@gmail.com"))
            viewModel.processIntent(LoginContract.Intent.OnPasswordChange(""))

            //WHEN
            viewModel.processIntent(LoginContract.Intent.OnLoginClicked)

            //THEN
            assertThat(viewModel.state.value.errorMessage).isNotNull()

        }

    @Test
    fun `given valid credentials, when OnLoginClicked, then isLoading becomes true`() = runTest {
        // GIVEN
        val testSubject = LoginViewModelTestFactory.givenALoginViewModel()
        val viewModel = testSubject.viewModel

        viewModel.processIntent(LoginContract.Intent.OnEmailChange("valid@email.com"))
        viewModel.processIntent(LoginContract.Intent.OnPasswordChange("123456"))

        // WHEN
        viewModel.processIntent(LoginContract.Intent.OnLoginClicked)
        testDispatcher.scheduler.runCurrent()

        // THEN
        assertThat(viewModel.state.value.isLoading).isTrue()

        assertThat(viewModel.state.value.errorMessage).isNull()
        assertThat(viewModel.state.value.emailFormatError).isNull()
    }

}
