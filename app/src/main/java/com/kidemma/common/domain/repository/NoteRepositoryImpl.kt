package com.kidemma.common.domain.repository

import com.kidemma.common.data.local.dao.domain.Note
import com.kidemma.common.data.local.dao.NoteDao
import com.kidemma.common.data.local.dao.domain.toDomain
import com.kidemma.common.data.local.dao.domain.toDomainList
import com.kidemma.common.data.local.dao.domain.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

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

class NoteRepositoryImpl(
    private val dao: NoteDao
) : NoteRepository {

    override fun getNotes(): Flow<List<Note>> {
        return dao.getAllNotes().map { entities ->
            entities.toDomainList()
        }
    }

    override suspend fun insertNote(note: Note) {
        dao.insertNote(note.toEntity())
    }

    override suspend fun deleteNote(note: Note) {
        dao.deleteNote(note.toEntity())
    }
}
