package com.kidemma.home_admin.tabs.families

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kidemma.authentication.domain.model.FamilyUiModel
import com.kidemma.home_admin.tabs.families.domain.FamilyMock
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
/*
 * File: FamiliesTabViewModel
 * Description: [Short description]
 *
 * Created by: Laura Zermeño Pichardo
 * Created on: 27/02/26
 * Last modified: 06/03/26
 */
class FamiliesTabViewModel(): ViewModel() {

    private var allFamilies : List<FamilyUiModel> = emptyList()
    private val _state = MutableStateFlow(FamiliesTabContract.State(isLoading = true))
    val state: StateFlow<FamiliesTabContract.State> = _state

    init {
        loadFamilies()
    }

    fun onEvent(event: FamiliesTabContract.Event) {
        when(event){
            is FamiliesTabContract.Event.OnSearchQueryChange -> {
                val filtered = allFamilies.filter {
                    it.familyName.contains(event.query, ignoreCase = true)
                }

                _state.value = _state.value.copy(
                    searchQuery = event.query,
                    families = if (event.query.isBlank()) allFamilies else filtered
                )
            }
        }
    }

    private fun loadFamilies(){
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            delay(1000)
            val families = FamilyMock.familyList
            allFamilies = families

            _state.value = _state.value.copy(
                isLoading = false,
                families = allFamilies
            )

        }
    }

}