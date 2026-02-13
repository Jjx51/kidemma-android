package com.kidemma.samplearchitect.data.remote.todo

import com.kidemma.samplearchitect.data.model.todo.TodoResponse
import retrofit2.Response
import retrofit2.http.GET

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

interface TodoAPI {

    /**
     * Fetches a list of todo items from the API.
     *
     * @return A list of [TodoResponse] objects representing the todo items.
     */
    @GET("todos")
    suspend fun fetchTodoList(): Response<List<TodoResponse>>
}
