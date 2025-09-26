package kidemma.samplearchitect.data.remote.todo

import com.kidemma.samplearchitect.data.model.UseCaseResult
import com.kidemma.samplearchitect.data.model.todo.TodoResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

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

class TodoMockDataSource(
    private val iTodoAPI: MockTodoAPI,
    private val ioDispatcher: CoroutineDispatcher
) {
    suspend fun fetchTodoMockList():
            UseCaseResult<List<TodoResponse>> =
        withContext(ioDispatcher){
            iTodoAPI.fetchTodoList()
        }
}
