package com.kidemma.common.data.local.dao.entities

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

data class Note(val id: Int = 0, val title: String, val content: String)

// Convertir de DB a Dominio (Data -> Domain)
fun NoteEntity.toDomain(): Note {
    return Note(
        id = id,
        title = title,
        content = content
    )
}

// Convertir de Dominio a DB (Domain -> Data)
fun Note.toEntity(): NoteEntity {
    return NoteEntity(
        id = id,
        title = title,
        content = content
    )
}