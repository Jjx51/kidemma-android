package com.kidemma.samplearchitect.presentation.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kidemma.samplearchitect.presentation.viewmodel.TodoViewModel
import kidemma.samplearchitect.domain.FetchTodoUseCase

/**
 * Copyright (c) 2025 Accenture. All rights reserved.
 *
 * This software is the confidential and proprietary information of Accenture.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with
 * Android Mobility.
 * Creator: carlos.graniel
 * Created at: 24/09/2025
 *
 */

@Suppress("UNCHECKED_CAST")
class TodoViewModelFactory(
    private val fetchTodoUseCase: FetchTodoUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TodoViewModel::class.java)) {
            return TodoViewModel(fetchTodoUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
