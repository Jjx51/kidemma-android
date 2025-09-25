package com.kidemma.samplearchitect.data.model.todo

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

data class TodoResponse(
    val userId: Int,
    val id: Int,
    val title: String,
    val completed: Boolean
)
