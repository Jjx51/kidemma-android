package com.kidemma.home_admin.tabs.families.presentation

import com.kidemma.authentication.domain.model.FamilyUiModel

/*
 * File: FamiliesTabContract
 * Description: communication rules between the view and the viewModel of the families module
 *
 * Created by: Jorge Luis Hernández Núñez
 * Created on: 24/02/26
 * Last modified: 24/02/26
 */
object FamiliesTabContract {

    data class State (
        val isLoading : Boolean = false,
        val families: List<FamilyUiModel> = emptyList(),
        val searchQuery: String = "",
        val error: String? = null
    )

    sealed class Intent {
        data class OnSearchQueryChange(val query: String) : Intent()

    }
}