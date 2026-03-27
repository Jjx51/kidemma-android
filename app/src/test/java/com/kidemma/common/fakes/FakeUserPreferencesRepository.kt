package com.kidemma.common.fakes

import com.kidemma.common.data.local.UserPreferencesRepository
import com.kidemma.common.interactions.UserPreferencesInteraction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

/*
 * File: FakeUserPreferencesRepository
 * Description: [Short description]
 *
 * Created by: Jorge Luis Hernández Núñez
 * Created on: 24/02/26
 * Last modified: 24/02/26
 */
class FakeUserPreferencesRepository : UserPreferencesRepository {
    private val _isOnboardingCompleted = MutableStateFlow(false)

    override val isOnboardingCompleted: Flow<Boolean>
        get() {
            interactions.add(UserPreferencesInteraction.GET_ONBOARDING_STATUS)
            return _isOnboardingCompleted
        }

    val interactions = mutableListOf<UserPreferencesInteraction>()

    override suspend fun setOnboardingCompleted(completed: Boolean) {
        interactions.add(UserPreferencesInteraction.SET_ONBOARDING_COMPLETED)
        _isOnboardingCompleted.value = completed
    }
}
