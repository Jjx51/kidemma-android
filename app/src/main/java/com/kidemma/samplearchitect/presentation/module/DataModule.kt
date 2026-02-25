package com.kidemma.samplearchitect.presentation.module

import com.kidemma.samplearchitect.data.remote.todo.ITodoAPI
import com.kidemma.samplearchitect.data.remote.todo.RetrofitTodoAPI
import com.kidemma.samplearchitect.data.remote.todo.TodoAPIDataSource
import kidemma.samplearchitect.data.remote.todo.MockTodoAPI
import kidemma.samplearchitect.data.remote.todo.TodoMockDataSource
import kidemma.samplearchitect.data.repository.TodoRepository
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

/**
 * Copyright (c) 2025 Accenture. All rights reserved.
 *
 * This software is the confidential and proprietary information of Accenture.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with
 * Android Mobility.
 * Creator: carlos.graniel
 * Created at: 25/02/2026
 *
 */

val dataModule = module {
    single<ITodoAPI> { RetrofitTodoAPI(get()) }
    single { TodoAPIDataSource(get(), get()) }
    single { MockTodoAPI() }
    single { TodoMockDataSource(get(), get()) }
    single { TodoRepository(get(), get()) }
    single { Dispatchers.IO }
}