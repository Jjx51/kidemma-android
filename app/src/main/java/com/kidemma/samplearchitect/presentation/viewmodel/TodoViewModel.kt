package com.kidemma.samplearchitect.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.kidemma.samplearchitect.data.model.UseCaseResult
import com.kidemma.samplearchitect.data.model.todo.TodoResponse
import com.kidemma.samplearchitect.presentation.intent.TodoIntent
import com.kidemma.samplearchitect.presentation.intent.TodoSideEffect
import kidemma.samplearchitect.domain.FetchTodoUseCase
import kidemma.samplearchitect.enums.DataSource
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.receiveAsFlow
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

class TodoViewModel(private val fetchTodoUseCase: FetchTodoUseCase) : BaseViewModel() {

    private val _uiState = MutableStateFlow<UIStates<List<TodoResponse>>>(UIStates.Init)
    val uiState: StateFlow<UIStates<List<TodoResponse>>> = _uiState

    private val _sideEffect = Channel<TodoSideEffect>(Channel.UNLIMITED)
    val sideEffect = _sideEffect.receiveAsFlow()

    init {
        sendIntent(TodoIntent.LoadTodos)
    }

    fun sendIntent(intent: TodoIntent) {
        viewModelScope.launch {
            when (intent) {
                is TodoIntent.LoadTodos -> fetchTodos(DataSource.API)
                is TodoIntent.NavigateToDetails -> _sideEffect.send(
                    TodoSideEffect.NavigateToDetails(intent.todoId)
                )

                is TodoIntent.ShowSnackbar -> _sideEffect.send(
                    TodoSideEffect.ShowSnackbar("TODOs cargados")
                )
            }
        }
    }

    fun fetchTodos(dataSource: DataSource) {
        viewModelScope.launch {
            _uiState.value = UIStates.Loading

            when (val result = fetchTodoUseCase(dataSource)) {
                is UseCaseResult.Success -> {
                    _uiState.value = UIStates.Success(result.data)
                }

                is UseCaseResult.Error -> {
                    _uiState.value = UIStates.Error(
                        result.exception.message ?: "Error desconocido"
                    )
                }

                is UseCaseResult.Unauthorized -> {
                    _uiState.value = UIStates.Unauthorized(result.message)
                }
            }
        }
    }
}
