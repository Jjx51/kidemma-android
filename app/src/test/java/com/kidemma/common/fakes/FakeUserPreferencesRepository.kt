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
class FakeUserPreferencesRepository(
    initialIsAdminUser: Boolean = false
) : UserPreferencesRepository {
    private val _isOnboardingCompleted = MutableStateFlow(false)
    private val _isAdminUser = MutableStateFlow(initialIsAdminUser)

    val interactions = mutableListOf<UserPreferencesInteraction>()

    override val isOnboardingCompleted: Flow<Boolean>
        get() {
            interactions.add(UserPreferencesInteraction.GET_ONBOARDING_STATUS)
            return _isOnboardingCompleted
        }

    override suspend fun setOnboardingCompleted(completed: Boolean) {
        interactions.add(UserPreferencesInteraction.SET_ONBOARDING_COMPLETED)
        _isOnboardingCompleted.value = completed
    }

    override val isAdminUser: Flow<Boolean>
        get() {
            interactions.add(UserPreferencesInteraction.GET_IS_ADMIN_USER)
            return _isAdminUser
        }

    override suspend fun setIsAdminUser(isAdmin: Boolean) {
        interactions.add(UserPreferencesInteraction.SET_IS_ADMIN_USER)
        _isAdminUser.value = isAdmin
    }
}