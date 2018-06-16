package com.projectblejder.jointcash.infrastructure

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query

@Dao
interface PersonsDao {

    @Query("SELECT * FROM persons")
    fun all(): List<PersonEntity>

}
