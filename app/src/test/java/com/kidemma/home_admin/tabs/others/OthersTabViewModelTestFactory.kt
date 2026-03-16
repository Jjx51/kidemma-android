package com.kidemma.home_admin.tabs.others

import com.kidemma.common.fakes.FakeUserPreferencesRepository
import com.kidemma.home_admin.tabs.others.models.OthersTabViewModelTestSubject
import com.kidemma.home_admin.tabs.others.presentation.OthersTabViewModelImpl

/*
 * File: OthersTabViewModelTestFactory
 * Description: Factory for creating OthersTabViewModelImpl test subjects
 * Created by: Lino Alonso Hdez
 * Created on: 16/03/26
 * Last modified: 16/03/26
 */
object OthersTabViewModelTestFactory {

    fun givenAnOthersTabViewModel(
        isAdminUser: Boolean = false
    ): OthersTabViewModelTestSubject {
        val fakeUserPreferencesRepository = FakeUserPreferencesRepository(
            initialIsAdminUser = isAdminUser
        )

        return OthersTabViewModelTestSubject(
            viewModel = OthersTabViewModelImpl(
                userPreferencesRepository = fakeUserPreferencesRepository
            ),
            fakeUserPreferencesRepository = fakeUserPreferencesRepository
        )
    }
}
