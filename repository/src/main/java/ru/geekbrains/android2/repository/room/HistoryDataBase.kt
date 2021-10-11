package ru.geekbrains.android2.repository.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(HistoryEntity::class), version = 1, exportSchema = false)
abstract class HistoryDataBase : RoomDatabase() {
    abstract fun historyDao(): HistoryDao

    companion object {
         const val DB_NAME = "HistoryDB"
    }
}