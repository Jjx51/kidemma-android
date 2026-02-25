package com.kidemma.common.data.local

import kotlinx.coroutines.flow.Flow

/*
 * File: UserPreferencesRepository
 * Description: [Short description]
 *
 * Created by: Jorge Luis Hernández Núñez
 * Created on: 24/02/26
 * Last modified: 24/02/26
 */
interface UserPreferencesRepository {
    val isOnboardingCompleted: Flow<Boolean>
    suspend fun setOnboardingCompleted(completed: Boolean)
}