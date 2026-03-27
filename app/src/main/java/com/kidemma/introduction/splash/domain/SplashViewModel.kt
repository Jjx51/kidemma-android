package com.kidemma.introduction.splash.domain

import com.kidemma.introduction.splash.presentation.SplashContract
import kotlinx.coroutines.flow.SharedFlow

/*
 * File: SplashViewModel
 * Description: [Short description]
 *
 * Created by: Jorge Luis Hernández Núñez
 * Created on: 24/02/26
 * Last modified: 24/02/26
 */
interface SplashViewModel {
    fun processIntent(intent: SplashContract.Intent)
    val effects: SharedFlow<SplashContract.Effect>
}
