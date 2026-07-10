package com.kidemma.home_admin.tabs.others.models

import com.kidemma.common.fakes.FakeUserPreferencesRepository
import com.kidemma.home_admin.tabs.others.presentation.OthersTabViewModel

/*
 * File: OthersTabViewModelTestSubject
 * Description: Test subject holder for OthersTabViewModelImpl tests
 * Created by: Lino Alonso Hdez
 * Created on: 16/03/26
 * Last modified: 16/03/26
 */
data class OthersTabViewModelTestSubject(
    val viewModel: OthersTabViewModel,
    val fakeUserPreferencesRepository: FakeUserPreferencesRepository
)
