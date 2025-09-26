package com.kidemma.samplearchitect.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kidemma.samplearchitect.data.model.todo.TodoResponse
import com.kidemma.samplearchitect.presentation.ui.theme.KidemmaTheme
import com.kidemma.samplearchitect.presentation.viewmodel.TodoUiEvent
import com.kidemma.samplearchitect.presentation.viewmodel.TodoViewModel
import com.kidemma.samplearchitect.presentation.viewmodel.UIStates
import org.koin.androidx.viewmodel.ext.android.viewModel

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

class TodoComponentActivity : ComponentActivity() {

    private val todoViewModel: TodoViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KidemmaTheme {
                TodoScreen()
            }
        }
    }

    @Composable
    fun TodoScreen(viewModel: TodoViewModel = todoViewModel) {

        val uiState by viewModel.uiState.collectAsState()
        val snackbarHostState = remember { SnackbarHostState() }

        Scaffold(
            snackbarHost = { SnackbarHost(snackbarHostState) }
        ) { paddingValues ->

            LaunchedEffect(Unit) {
                viewModel.uiEvent.collect { event ->
                    when (event) {
                        is TodoUiEvent.ShowSnackbar -> {
                            snackbarHostState.showSnackbar(
                                message = event.message,
                                actionLabel = "Close"
                            )
                        }

                        is TodoUiEvent.NavigateToDetails -> {
                            Log.d(
                                "TodoScreen", "Navigate to details of ${event.todoId}"
                            )
                        }
                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 24.dp)
                    .padding(paddingValues)
            ) {
                when (uiState) {
                    is UIStates.Init -> Text("Initial Screen")
                    is UIStates.Loading -> {
                        Box(modifier = Modifier.fillMaxSize()) {
                            CircularProgressIndicator(
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }

                    is UIStates.Success -> {
                        val todos = (
                                uiState as UIStates.Success<List<TodoResponse>>).value
                            ?: emptyList()
                        LazyColumn {
                            items(todos) { todo ->
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable {
                                            viewModel.onTodoClicked(
                                                todo.id.toString()
                                            )
                                        }
                                        .padding(16.dp)
                                ) {
                                    Text(todo.title)
                                }
                            }
                        }
                    }

                    is UIStates.Error -> {
                        Log.e("TodoScreen", (uiState as UIStates.Error).message)
                    }

                    is UIStates.Unauthorized -> {
                        Log.e("TodoScreen", (uiState as UIStates.Unauthorized).message)
                    }
                }
            }
        }
    }
}
