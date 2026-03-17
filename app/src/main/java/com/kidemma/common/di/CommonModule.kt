package com.kidemma.common.di

import com.kidemma.common.data.local.UserPreferencesRepository
import com.kidemma.common.data.local.UserPreferencesRepositoryImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

/*
 * File: CommonModule
 * Description: [Short description]
 *
 * Created by: Jorge Luis Hernández Núñez
 * Created on: 24/02/26
 * Last modified: 24/02/26
 */
val commonModule = module {
    singleOf(::UserPreferencesRepositoryImpl) bind UserPreferencesRepository::class
}
