package com.kidemma.samplearchitect.presentation

import android.os.Bundle
import android.widget.Toast
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
                Box(modifier = Modifier.padding(top = 16.dp)) {
                    TodoScreen()
                }
            }
        }
    }

    @Composable
    fun TodoScreen(viewModel: TodoViewModel = todoViewModel) {
        val uiState by viewModel.uiState.collectAsState()

        val context = LocalContext.current

        LaunchedEffect(Unit) {
            viewModel.uiEvent.collect { event ->
                when (event) {
                    is TodoUiEvent.ShowSnackbar -> {
                        Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                    }

                    is TodoUiEvent.NavigateToDetails -> {
                        println("Navigate to Screen detail to id ${event.todoId}")
                    }
                }
            }
        }

        when (uiState) {
            is UIStates.Init -> {
                Text("Initial Screen")
            }

            is UIStates.Loading -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }

            is UIStates.Success -> {
                val todos = (uiState as UIStates.Success<List<TodoResponse>>).value ?: emptyList()
                LazyColumn {
                    items(todos) { todo ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { viewModel.onTodoClicked(todo.id.toString()) }
                                .padding(16.dp)
                        ) {
                            Text(todo.title)
                        }
                    }
                }
            }

            is UIStates.Error -> {
                Text("Error : ${(uiState as UIStates.Error).message}")
            }

            is UIStates.Unauthorized -> {
                Text("Session Expired : ${(uiState as UIStates.Unauthorized).message}")
            }
        }
    }
}
