package com.kidemma.login

import com.kidemma.login.models.LoginViewModelTestSubject
import com.kidemma.authentication.login.presentation.LoginViewModelImpl

/*
 * File: LoginViewModelTestFactory
 * Description: [Short description]
 *
 * Created by: Jorge Luis Hernández Núñez
 * Created on: 25/11/25
 * Last modified: 25/11/25
 */
object LoginViewModelTestFactory {

    fun givenALoginViewModel(): LoginViewModelTestSubject {

        val viewModel = LoginViewModelImpl()

        return LoginViewModelTestSubject(
            viewModel = viewModel
        )

    }

}
