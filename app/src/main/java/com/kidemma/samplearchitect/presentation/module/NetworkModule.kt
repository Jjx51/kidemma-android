package com.kidemma.samplearchitect.presentation.module

import com.kidemma.common.utils.createHttpClient
import com.kidemma.common.utils.createWebService
import com.kidemma.samplearchitect.data.remote.todo.TodoAPI
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

// Networking: Retrofit y OkHttp
val networkModule = module {
    single { createHttpClient() }
    single {
        createWebService<TodoAPI>(
            okHttpClient = get(),
            baseUrl = "https://jsonplaceholder.typicode.com/"
        )
    }
}