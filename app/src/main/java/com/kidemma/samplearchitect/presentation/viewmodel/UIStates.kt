package com.kidemma.samplearchitect.presentation.viewmodel

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

sealed class UIStates<out T : Any> {
    object Init : UIStates<Nothing>()
    object Loading : UIStates<Nothing>()
    class Unauthorized(val message: String) : UIStates<Nothing>()
    class Success<T : Any>(val value: T?) : UIStates<T>()
    class Error(val message: String) : UIStates<Nothing>()
}
