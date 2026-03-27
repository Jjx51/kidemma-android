package com.kidemma.common.domain.usecase

import com.kidemma.common.data.local.dao.domain.Note
import com.kidemma.common.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

/**
 * Copyright (c) 2025 Accenture. All rights reserved.
 *
 * This software is the confidential and proprietary information of Accenture.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with
 * Android Mobility.
 * Creator: carlos.graniel
 * Created at: 17/03/2026
 *
 */

class GetNotesUseCase(private val repository: NoteRepository) {
    operator fun invoke(): Flow<List<Note>> = repository.getNotes()
}
