package com.kidemma.samplearchitect.data.remote.todo

import com.kidemma.samplearchitect.data.model.UseCaseResult
import com.kidemma.samplearchitect.data.model.todo.TodoResponse
import com.kidemma.samplearchitect.data.remote.baseapi.BaseApi

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

class RetrofitTodoAPI(private val todoAPI: TodoAPI) : BaseApi(), ITodoAPI {

    override suspend fun fetchTodoList(
    ): UseCaseResult<List<TodoResponse>> {
        return executeRequest {
            todoAPI.fetchTodoList()
        }
    }
}
