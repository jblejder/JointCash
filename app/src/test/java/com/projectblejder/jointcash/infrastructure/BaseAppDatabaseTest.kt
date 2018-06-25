package com.projectblejder.jointcash.infrastructure

import android.arch.persistence.room.Room
import org.junit.Before
import org.robolectric.RuntimeEnvironment

abstract class BaseAppDatabaseTest {

    lateinit var db: AppDatabase

    @Before
    fun setUpDatabase() {
        val context = RuntimeEnvironment.systemContext
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
                .allowMainThreadQueries()
                .build()
    }
}
