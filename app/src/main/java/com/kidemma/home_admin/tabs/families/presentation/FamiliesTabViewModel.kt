package com.kidemma.home_admin.tabs.families.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kidemma.authentication.domain.model.FamilyUiModel
import com.kidemma.common.validation.KidemmaValidationRules
import com.kidemma.common.validation.KidemmaValidator
import com.kidemma.home_admin.tabs.families.domain.FamiliesTabMockProvider
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/*
 * File: FamiliesTabViewModel.kt
 * Description: View model for the Families tab screen with search validation logic.
 *
 * Created by: Laura Zermeño Pichardo
 * Created on: 27/02/26
 * Last modified: 19/05/26
 */
class FamiliesTabViewModel : ViewModel() {

    private var allFamilies: List<FamilyUiModel> = emptyList()
    private val _state = MutableStateFlow(FamiliesTabContract.State(isLoading = true))
    val state: StateFlow<FamiliesTabContract.State> = _state.asStateFlow()

    init {
        loadFamilies()
    }

    fun onIntent(intent: FamiliesTabContract.Intent) {
        when (intent) {
            is FamiliesTabContract.Intent.OnSearchQueryChange -> {
                val validationError = KidemmaValidator.validate(
                    value = intent.query,
                    rules = KidemmaValidationRules.filter()
                )

                val filtered = allFamilies.filter {
                    it.familyName.contains(intent.query, ignoreCase = true)
                }

                _state.update {
                    it.copy(
                        searchQuery = intent.query,
                        isSearchError = validationError != null,
                        searchError = validationError,
                        families = if (intent.query.isBlank() || validationError != null) allFamilies else filtered
                    )
                }
            }
        }
    }

    private fun loadFamilies(){
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            delay(1000)
            allFamilies = FamiliesTabMockProvider.familyList

            _state.value = _state.value.copy(
                isLoading = false,
                families = allFamilies
            )

        }
    }

}