package com.kidemma.my_profile.di

import com.kidemma.my_profile.presentation.MyProfileViewModel
import com.kidemma.my_profile.presentation.MyProfileViewModelImpl
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module


/**
 * Description: Koin module for MyProfile feature, providing necessary dependencies.
 * @author Arturo Rivera Morales
 */
val myProfileModule = module {
    viewModelOf(::MyProfileViewModelImpl) bind MyProfileViewModel::class
}