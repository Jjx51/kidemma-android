package com.kidemma.introduction

import com.kidemma.introduction.splash.domain.SplashViewModel
import com.kidemma.introduction.splash.presentation.SplashViewModelImpl
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

/*
 * File: IntroductionModule
 * Description: [Short description]
 *
 * Created by: Jorge Luis Hernández Núñez
 * Created on: 24/02/26
 * Last modified: 24/02/26
 */
val introductionModule = module {
    viewModelOf(::SplashViewModelImpl) bind SplashViewModel::class
}