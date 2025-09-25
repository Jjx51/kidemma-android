package com.kidemma.samplearchitect.data.remote.baseapi

import com.kidemma.samplearchitect.data.model.UseCaseResult
import retrofit2.HttpException
import retrofit2.Response

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

abstract class BaseApi {

    // Implementación de request genérico
    protected suspend fun <T : Any> executeRequest(apiCall: suspend () -> Response<T>): UseCaseResult<T> {
        return try {
            val response = apiCall.invoke()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    UseCaseResult.Success(body)
                } else {
                    UseCaseResult.Error(kotlin.Exception("Response body is null"))
                }
            } else {
                UseCaseResult.Error(kotlin.Exception("Error ${response.code()}: ${response.message()}"))
            }
        } catch (ex: HttpException) {
            UseCaseResult.Error(ex)
        }
    }
}
