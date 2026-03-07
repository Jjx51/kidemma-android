package com.kidemma.home_admin.tabs.families

import com.kidemma.authentication.domain.model.FamilyUiModel

object FamiliesTabContract {

    data class State (
        val isLoading : Boolean = false,
        val families: List<FamilyUiModel> = emptyList(),
        val searchQuery: String = "",
        val error: String? = null
    )

    sealed class Event {
        data class OnSearchQueryChange(val query: String) : Event()

    }
}