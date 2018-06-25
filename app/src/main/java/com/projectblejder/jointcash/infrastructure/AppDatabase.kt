package com.projectblejder.jointcash.infrastructure

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import com.projectblejder.jointcash.infrastructure.dao.GroupsDao
import com.projectblejder.jointcash.infrastructure.dao.PersonGroupDao
import com.projectblejder.jointcash.infrastructure.dao.PersonsDao
import com.projectblejder.jointcash.infrastructure.models.Converters
import com.projectblejder.jointcash.infrastructure.models.Group
import com.projectblejder.jointcash.infrastructure.models.Person
import com.projectblejder.jointcash.infrastructure.models.PersonGroup

@Database(
        entities = [
            Person::class,
            Group::class,
            PersonGroup::class
        ],
        version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        fun build(context: Context) =
                Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "app-database").build()
    }

    abstract fun persons(): PersonsDao

    abstract fun groups(): GroupsDao

    abstract fun personGroup(): PersonGroupDao
}
