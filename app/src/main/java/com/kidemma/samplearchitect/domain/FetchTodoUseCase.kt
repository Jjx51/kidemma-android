package kidemma.samplearchitect.domain

import com.kidemma.samplearchitect.data.model.UseCaseResult
import com.kidemma.samplearchitect.data.model.todo.TodoResponse
import kidemma.samplearchitect.data.repository.TodoRepository
import kidemma.samplearchitect.enums.DataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
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

class FetchTodoUseCase(
    private val todoRepository: TodoRepository,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend operator fun invoke(
        dataSource: DataSource
    ) : UseCaseResult<List<TodoResponse>> {
        return withContext(defaultDispatcher) {
            when (dataSource) {
                DataSource.API -> todoRepository.fetchTodoList()
                DataSource.MOCK -> todoRepository.fetchTodoListMock()
            }
        }
    }
}
