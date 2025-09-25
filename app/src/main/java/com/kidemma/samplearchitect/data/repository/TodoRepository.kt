package kidemma.samplearchitect.data.repository

import com.kidemma.samplearchitect.data.model.UseCaseResult
import com.kidemma.samplearchitect.data.model.todo.TodoResponse
import kidemma.samplearchitect.data.remote.todo.TodoMockDataSource
import com.kidemma.samplearchitect.data.remote.todo.TodoAPIDataSource

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

class TodoRepository(
    private val todoAPIDataSource: TodoAPIDataSource,
    private val todoMockDataSource: TodoMockDataSource
) {

    suspend fun fetchTodoList(): UseCaseResult<List<TodoResponse>> {
        return todoAPIDataSource.fetchTodoList()
    }

    suspend fun fetchTodoListMock(): UseCaseResult<List<TodoResponse>> {
        return todoMockDataSource.fetchTodoMockList()
    }
}
