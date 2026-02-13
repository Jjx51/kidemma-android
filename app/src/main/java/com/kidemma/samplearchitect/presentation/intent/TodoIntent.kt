package com.kidemma.samplearchitect.presentation.intent

/**
 * Copyright (c) 2025 Accenture. All rights reserved.
 *
 * This software is the confidential and proprietary information of Accenture.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with
 * Android Mobility.
 * Creator: carlos.graniel
 * Created at: 02/10/2025
 *
 */

sealed class TodoIntent {
    object LoadTodos : TodoIntent()
    data class NavigateToDetails(val todoId : String) : TodoIntent()
    object ShowSnackbar : TodoIntent()
}

sealed class TodoSideEffect{
    data class ShowSnackbar(val message: String) : TodoSideEffect()
    data class NavigateToDetails(val todoId : String) : TodoSideEffect()
}
