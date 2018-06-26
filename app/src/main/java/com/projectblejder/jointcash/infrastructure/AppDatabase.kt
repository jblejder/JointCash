package com.projectblejder.jointcash.infrastructure

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import com.projectblejder.jointcash.infrastructure.converters.ExpenseTypeConverter
import com.projectblejder.jointcash.infrastructure.dao.GroupsDao
import com.projectblejder.jointcash.infrastructure.dao.PersonGroupDao
import com.projectblejder.jointcash.infrastructure.dao.PersonsDao
import com.projectblejder.jointcash.infrastructure.models.*
import io.reactivex.Completable
import io.reactivex.subjects.CompletableSubject

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
abstract class AppDatabase : RoomDatabase() {

    companion object {
        private var db: AppDatabase? = null

        val instance: AppDatabase
            get() {
                if (db == null) {
                    throw IllegalStateException("DB instance not initialized")
                }
                return db!!
            }

        fun initialize(context: Context): Completable {
            if (db != null) {
                return Completable.error(IllegalStateException("DB already initialized"))
            }
            val subject = CompletableSubject.create()
            db = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "app-database")
                    .triggerSubjectOnCreate(subject)
                    .build()
            return subject
        }

        private fun <T : RoomDatabase> RoomDatabase.Builder<T>.triggerSubjectOnCreate(subject: CompletableSubject): RoomDatabase.Builder<T> {
            return this.addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    subject.onComplete()
                }
            })
        }

    }

    abstract fun persons(): PersonsDao

    abstract fun groups(): GroupsDao

    abstract fun personGroup(): PersonGroupDao
}
