package com.projectblejder.jointcash.infrastructure

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.support.annotation.WorkerThread
import com.projectblejder.jointcash.infrastructure.converters.ExpenseTypeConverter
import com.projectblejder.jointcash.infrastructure.dao.GroupsDao
import com.projectblejder.jointcash.infrastructure.dao.PersonGroupDao
import com.projectblejder.jointcash.infrastructure.dao.PersonsDao
import com.projectblejder.jointcash.infrastructure.models.*

@Database(
        entities = [
            Person::class,
            Group::class,
            PersonGroup::class,
            Expense::class,
            PersonExpense::class
        ],
        version = 1
)
@TypeConverters(ExpenseTypeConverter::class)
abstract class AppDatabase() : RoomDatabase() {

    abstract fun persons(): PersonsDao

    abstract fun groups(): GroupsDao

//    abstract fun personGroup(): PersonGroupDao
}

class AppDatabaseProvider {

    lateinit var instance: AppDatabase

    @WorkerThread
    fun initialize(factory: AppDatabaseFactory) {
        instance = factory.build()
    }

    interface AppDatabaseFactory {
        fun build(): AppDatabase
    }
}
