package com.projectblejder.jointcash.infrastructure

import android.arch.persistence.room.Room
import android.content.Context
import com.projectblejder.jointcash.infrastructure.models.Group
import com.projectblejder.jointcash.infrastructure.models.Person


class AppDatabaseFactory(private val context: Context) : AppDatabaseProvider.AppDatabaseFactory {

    override fun build(): AppDatabase {
        return buildDb(context).also {
            populateInitialData(it)
        }
    }

    private fun populateInitialData(appDatabase: AppDatabase) {
        if (isInitialized(appDatabase)) {
            return
        }
        appDatabase.persons().create(Person(1, "{you}"))
        appDatabase.groups().create(Group(1, "{general}"))
    }

    private fun isInitialized(appDatabase: AppDatabase): Boolean {
        val personsInitialized = appDatabase.persons().all().isNotEmpty()
        val groupsInitialized = appDatabase.groups().all().isNotEmpty()
        return personsInitialized && groupsInitialized
    }

    private fun buildDb(context: Context) = Room
            .databaseBuilder(context.applicationContext, AppDatabase::class.java, "app-database")
            .build()
}
