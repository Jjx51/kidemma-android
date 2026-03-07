package com.kidemma.introduction.di

import com.kidemma.home_admin.tabs.families.FamiliesTabViewModel
import com.kidemma.introduction.onboarding.presentation.OnboardingViewModel
import com.kidemma.introduction.onboarding.presentation.OnboardingViewModelImpl
import com.kidemma.introduction.splash.domain.SplashViewModel
import com.kidemma.introduction.splash.presentation.SplashViewModelImpl
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

/*
 * File: IntroductionModule
 * Description: Used for the injection dependencies for feature Introduction , at this
 * moment splash and onboarding
 *
 * Created by: Jorge Luis Hernández Núñez
 * Created on: 24/02/26
 * Last modified: 24/02/26
 */
val introductionModule = module {
    viewModelOf(::SplashViewModelImpl) bind SplashViewModel::class
    viewModelOf(::OnboardingViewModelImpl) bind OnboardingViewModel::class
}