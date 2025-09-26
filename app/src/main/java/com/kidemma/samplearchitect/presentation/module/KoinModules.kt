package com.kidemma.samplearchitect.presentation.module

import com.google.gson.GsonBuilder
import com.kidemma.samplearchitect.data.remote.todo.ITodoAPI
import com.kidemma.samplearchitect.data.remote.todo.RetrofitTodoAPI
import com.kidemma.samplearchitect.data.remote.todo.TodoAPI
import com.kidemma.samplearchitect.data.remote.todo.TodoAPIDataSource
import com.kidemma.samplearchitect.presentation.viewmodel.TodoViewModel
import kidemma.samplearchitect.data.remote.todo.MockTodoAPI
import kidemma.samplearchitect.data.remote.todo.TodoMockDataSource
import kidemma.samplearchitect.data.repository.TodoRepository
import kidemma.samplearchitect.domain.FetchTodoUseCase
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

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

fun createAppModules(): Module = module() {

    single {
        createWebService<TodoAPI>(
            okHttpClient = createHttpClient(),
            baseUrl = "https://jsonplaceholder.typicode.com/"
        )
    }

    single { Dispatchers.IO }

    single<ITodoAPI> { RetrofitTodoAPI(get()) }

    single { TodoAPIDataSource(get(), get()) }
    single { MockTodoAPI() }
    single { TodoMockDataSource(get(), get()) }
    single { TodoRepository(get(), get()) }
    single { FetchTodoUseCase(get(), get()) }

    factory { TodoViewModel(get()) }

    viewModel { TodoViewModel(get()) }
}

inline fun <reified T> createWebService(
    okHttpClient: OkHttpClient,
    baseUrl: String
): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .client(okHttpClient)
        .build()
    return retrofit.create(T::class.java)
}

fun createHttpClient(): OkHttpClient {
    val interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    val builder = OkHttpClient.Builder()
        .readTimeout(60, TimeUnit.SECONDS)
        .addInterceptor(interceptor)

    return builder.build()
}
