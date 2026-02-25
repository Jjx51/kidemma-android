package com.kidemma.samplearchitect.presentation.module

import kidemma.samplearchitect.domain.FetchTodoUseCase
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

// Domain: Casos de uso
val domainModule = module {
    factory { FetchTodoUseCase(get(), get()) }
}
