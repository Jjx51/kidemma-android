package com.kidemma.home_admin.tabs.kids

import com.kidemma.home_admin.tabs.kids.models.KidsTabViewModelTestSubject
import com.kidemma.home_admin.tabs.kids.presentation.KidsTabViewModelImpl

/*
 * File: KidsTabViewModelTestFactory.kt
 * Description: Factory for creating KidsTabViewModel test subjects.
 *
 * Created by: José Manuel Carrillo Torres
 * Created on: 11/03/26
 * Last modified: 11/03/26
 */

object KidsTabViewModelTestFactory {
    fun givenAKidsViewModel(): KidsTabViewModelTestSubject {
        return KidsTabViewModelTestSubject(
            viewModel = KidsTabViewModelImpl(),
        )
    }
}

