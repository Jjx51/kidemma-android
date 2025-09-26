package com.kidemma.samplearchitect.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.kidemma.samplearchitect.data.model.UseCaseResult
import com.kidemma.samplearchitect.data.model.todo.TodoResponse
import kidemma.samplearchitect.domain.FetchTodoUseCase
import kidemma.samplearchitect.enums.DataSource
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

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

sealed class TodoUiEvent {
    data class NavigateToDetails(val todoId: String) : TodoUiEvent()
    data class ShowSnackbar(val message: String) : TodoUiEvent()
}

class TodoViewModel(private val fetchTodoUseCase: FetchTodoUseCase) : BaseViewModel() {

    private val _uiState = MutableStateFlow<UIStates<List<TodoResponse>>>(UIStates.Init)
    val uiState: StateFlow<UIStates<List<TodoResponse>>> = _uiState

    private val _uiEvent = MutableSharedFlow<TodoUiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    init {
        initTodoProcess()
    }

    private fun initTodoProcess() {
        viewModelScope.launch {
            fetchTodos(DataSource.API)
        }
    }

    fun fetchTodos(dataSource: DataSource) {
        viewModelScope.launch {
            _uiState.value = UIStates.Loading

            when (val result = fetchTodoUseCase(dataSource)) {
                is UseCaseResult.Success -> {
                    _uiState.value = UIStates.Success(result.data)
                    /*_uiState.value = UIStates.Error("Simulated error for testing")
                    _uiEvent.emit(
                        TodoUiEvent.ShowSnackbar(
                            "TODOs cargados"))
                     */
                }

                is UseCaseResult.Error -> {
                    _uiState.value = UIStates.Error(
                        result.exception.message ?: "Error desconocido"
                    )
                    _uiEvent.emit(
                        TodoUiEvent.ShowSnackbar(
                            "Error al cargar los TODOs"
                        )
                    )
                }

                is UseCaseResult.Unauthorized -> {
                    _uiState.value = UIStates.Unauthorized(result.message)
                    _uiEvent.emit(TodoUiEvent.ShowSnackbar("Sesión expirada"))
                }
            }
        }
    }

    fun onTodoClicked(todoId: String) {
        viewModelScope.launch {
            _uiEvent.emit(TodoUiEvent.NavigateToDetails(todoId))
        }
    }
}
