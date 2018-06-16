package com.projectblejder.jointcash.infrastructure

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(
        entities = [PersonEntity::class],
        version = 1
)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        fun build(context: Context) =
                Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "app-database").build()
    }

    abstract fun persons(): PersonsDao
}
