package com.kidemma.common.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kidemma.common.data.local.dao.NoteDao
import com.kidemma.common.data.local.dao.entities.NoteEntity

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

@Database(
    entities = [NoteEntity::class],
    version = 1,
    exportSchema = false
)
abstract class KidemmaDatabase : RoomDatabase() {

    abstract val noteDao: NoteDao

    companion object {
        const val DATABASE_NAME = "kidemma_db"
    }
}
