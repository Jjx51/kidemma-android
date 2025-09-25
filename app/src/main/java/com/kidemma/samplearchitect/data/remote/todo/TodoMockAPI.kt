package kidemma.samplearchitect.data.remote.todo

import com.kidemma.samplearchitect.data.model.UseCaseResult
import com.kidemma.samplearchitect.data.model.todo.TodoResponse
import com.kidemma.samplearchitect.data.remote.todo.ITodoAPI

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

class TodoMockAPI : ITodoAPI {
    override suspend fun fetchTodoList(): UseCaseResult<List<TodoResponse>> {
        return UseCaseResult.Success(
            listOf(
                TodoResponse(
                    id = 1,
                    userId = 1,
                    title = "Todo 1",
                    completed = false
                ),
                TodoResponse(
                    id = 2,
                    userId = 1,
                    title = "Todo 2",
                    completed = true
                ),
                TodoResponse(
                    id = 3,
                    userId = 1,
                    title = "Todo 2",
                    completed = true
                ),
                TodoResponse(
                    id = 4,
                    userId = 1,
                    title = "Todo 2",
                    completed = true
                ),
                TodoResponse(
                    id = 5,
                    userId = 1,
                    title = "Todo 2",
                    completed = true
                )
            )
        )
    }
}
